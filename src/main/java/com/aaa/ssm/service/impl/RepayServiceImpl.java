package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.*;
import com.aaa.ssm.service.RepayService;
import com.aaa.ssm.util.DEBXUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * className:RepayServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-22 09:59
 */
@Transactional //事物
@Service
public class RepayServiceImpl implements RepayService {

    //依赖注入
    @Autowired
    private RepayDao repayDao;

    @Autowired
    private AduitBidDao aduitBidDao;

    @Autowired
    private BiaodeDao biaodeDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private TenderDao tenderDao;

    /**
     * 满标二审通过操作
     * 1、更改标的状态
     * 2、往标的审核表中插入数据
     * 3、生成还款计划表
     * 4、用户账户表（借款人账户余额：+借款金额；待还金额：本息和
     *                投资人账户：冻结金额=原冻结金额-投资金额 、代收本金=投资金额、代收利息=总利息  ）
     * 5、账户流水表（变动）
     * {PAYMENT=按月付息，到期还本, DAYS=4, APR=0.05, BORROWMONEY=99995,
     * APPLICANT=贾源浩, TIMELIMIT=3, USERID=3, DANBAOSTYLE=车辆之押贷款,
     * WINBIDMONEY=100000, BORROWNUM=BD0000004, BORROWID=26, ADUITRESULT=1,
     * OPERATORID=44, ADUITREMARK=rtyyh, submitArr=[4, 4, 4, 4, 4]}
     * @param map
     * @return
     */
    @Transactional
    @Override
    public int repayPlan(Map map) {
        System.out.println(map);
        Boolean flag = true;
       //1、更改标的状态（判断）
        Integer aduitresult = Integer.valueOf(map.get("ADUITRESULT")+"");
        if(aduitresult==1) {//满标二审通过
            //更新标的状态
            int m=biaodeDao.updatebidStatuOne(map);
            if(m==0){
                flag=false;
            }
            //生成还款计划表
            Boolean isSuc = repayLoanPlan(map);
            if (isSuc==false){
                flag=false;
            }
            System.out.println("22222222222222222");
            //更改用户账户表,插入账户流水表
            Boolean isUpd = updateAccount(map);
            if (isUpd==false){
                flag=false;
            }
        }else {
            //更改标的状态（二审失败）
            int i = biaodeDao.updatebidStatu(map);
            if(i==0){
                flag=false;
            }
        }
        System.out.println("1111111111111111111");
        //2、往标的审核表中插入数据
        int s = aduitBidDao.add(map);
        if (s==0){
            flag=false;
        }
        if (flag)
            return 1;
        return 0;
    }

    /**
     * 生成还款计划表
     * @param map
     * @return
     */
    private Boolean repayLoanPlan(Map map){
        SqlSession sqlSession=null;
        Boolean flag=true;
        //本金
        double benjin = Double.parseDouble(map.get("BORROWMONEY").toString());
        //年利率
        double apr = Double.parseDouble(map.get("APR").toString());
        //还款总月数
        int totalMonth=Integer.valueOf(map.get("TIMELIMIT")+"");
        //调用等额本息工具类，算出总利息
        double lixi = DEBXUtil.getInterestCount(benjin, apr, totalMonth);
        map.put("LIXI",lixi);
        //本息和:每月应还金额
        double moneyPer=DEBXUtil.getPerMonthPrincipalInterest(benjin, apr, totalMonth);
        map.put("moneyPer",moneyPer);
        //每月应还利息
        Map<Integer, BigDecimal> perMonthInterest = DEBXUtil.getPerMonthInterest(benjin, apr, totalMonth);
        //期数
        Integer limit = Integer.valueOf(map.get("TIMELIMIT").toString());
        RepayDao repayDao = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            repayDao = sqlSession.getMapper(RepayDao.class);
            //获取当前系统时间
            Calendar calendar = Calendar.getInstance();
            if("等额本息".equals(map.get("PAYMENT"))){
                for (int i = 1; i <= limit-1; i++) {
                    //当前月份加i
                    calendar.add(Calendar.MONTH,i);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String format = df.format(calendar.getTime());
                    map.put("TIMELIMIT",i);
                    map.put("REPAYLIMIT",format);
                    //2.向还款表中插入数据
                    System.out.println(map);
                    int s = repayDao.add(map);
                    if (s==0){
                        flag=false;
                    }
                }
                //最后一期
                calendar.add(Calendar.MONTH,limit);
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                String dformat = d.format(calendar.getTime());
                map.put("TIMELIMIT",limit);
                map.put("REPAYLIMIT",dformat);
                int ss = repayDao.adds(map);
                if (ss==0){
                    flag=false;
                }
            }else if ("按月付息，到期还本".equals(map.get("PAYMENT"))){
                for(int i=1;i<=limit-2;i++){
                    //当前月份加i
                    calendar.add(Calendar.MONTH,i);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String format = df.format(calendar.getTime());
                    map.put("TIMELIMIT",i);
                    map.put("REPAYLIMIT",format);
                    //每月应还利息
                    double interstPer = Double.parseDouble(perMonthInterest.get(i)+"");
                    map.put("interstPer",interstPer);
                    //2.向还款表中插入数据
                    int s = repayDao.addOne(map);
                    if (s==0){
                        flag=false;
                    }
                }
                //倒数第二期插入期插入数据
                calendar.add(Calendar.MONTH,limit-1);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String format = df.format(calendar.getTime());
                map.put("TIMELIMIT",limit-1);
                map.put("REPAYLIMIT",format);
                System.out.println(map);
                int s = repayDao.addTwo(map);
                if (s==0){
                    flag=false;
                }
                //最后一期
                calendar.add(Calendar.MONTH,limit);
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                String dformat = d.format(calendar.getTime());
                map.put("TIMELIMIT",limit);
                map.put("REPAYLIMIT",dformat);
                int ss = repayDao.addTwos(map);
                if (ss==0){
                    flag=false;
                }
            }else {
                //到期付本付息
                calendar.add(Calendar.MONTH,limit);
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                String dformat = d.format(calendar.getTime());
                map.put("TIMELIMIT",limit);
                map.put("REPAYLIMIT",dformat);
                int bx = repayDao.addLast(map);
                if (bx==0){
                    flag=false;
                }
            }
            if (flag){
                sqlSession.commit();
            }else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        return flag;
    }

    /**
     * 更改用户账户表和账户流水表
     * 用户账户表（借款人账户余额：+借款金额；待还金额：本息和
     *                投资人账户：冻结金额=原冻结金额-投资金额 、代收本金=投资金额、代收利息=总利息  ）
     * 账户流水表（插入）
     * @param map
     * @return
     */
    private Boolean updateAccount(Map map){
        Integer userid=Integer.valueOf(map.get("USERID")+"");
        List<Map> userList=userInfoDao.getUserListById(userid);
        map.put("amount",userList.get(0).get("AMOUNT"));
        Boolean flag=true;
        /**
         * 更改借款人的账户
         * 1、借款人账户余额：+借款金额；待还金额：本息和  剩余信用额度=原剩余信用额度-招标金额（信用贷款）
         * 2、插入账户流水列表
         */
        //本金（借款金额）
        double benjin = Double.parseDouble(map.get("BORROWMONEY").toString());
        //年利率
        double apr = Double.parseDouble(map.get("APR").toString());
        //还款总月数
        int totalMonth=Integer.valueOf(map.get("TIMELIMIT")+"");
        //调用等额本息工具类，算出待还金额,即本息和
        double total = DEBXUtil.getPrincipalInterestCount(benjin, apr, totalMonth);
        map.put("total",total);
        System.out.println(benjin+"------------------------"+total+"------------------"+userid);
        int i=0;
        if ("信用贷款".equals(map.get("DANBAOSTYLE"))){
            i=userInfoDao.updateAccountOne(map);
        }else{
            System.out.println(map);
            i = userInfoDao.updateAccount(map);
        }
        if(i==0){
            flag=false;
        }
        //2、插入账户流水列表 id,userid,amount,flowdate,flowtypeid,changeamount
        int m = userInfoDao.addAccountFlow(map);
        if (m==0){
            flag=false;
        }
        /**
         * 更改投资人的账户
         * 1、投资人账户：冻结金额=原冻结金额-投资金额 、代收本金=投资金额+原本金、代收利息=总利息+原利息
         * 2、插入账户流水列表
         */
        //拿出投标人的tendernum
        List list = (ArrayList)map.get("submitArr");
        for (int j = 0; j < list.size(); j++) {
            //更改投标人账户
            String tendernum = (String) list.get(j);//取出每个投标人的tendernum
            //通过投资人tendernum查询投资列表
            List<Map> tenderList = tenderDao.getListByTenderNum(tendernum);
            //投资人的账户
            Integer tUserid = Integer.valueOf(tenderList.get(0).get("USERID")+"");
            List<Map> TuserList=userInfoDao.getUserListById(tUserid);
            //投标金额
            double tamount = Double.parseDouble(tenderList.get(0).get("TAMOUNT")+"");
            //调用等额本息工具类，算出总利息
            double collectlixi = DEBXUtil.getInterestCount(benjin, apr, totalMonth);
            //更改投资人的账户信息
            int n=userInfoDao.updateTrenderAccount(tamount,collectlixi,tUserid);
            if (n==0){
                flag=false;
            }
            double yamount = Double.parseDouble(TuserList.get(0).get("AMOUNT").toString());
            map.put("taccount",yamount-tamount);
            //插入账户流水列表 id,userid,amount,flowdate,flowtypeid,changeamount
            map.put("tamount",tamount);
            int add=userInfoDao.addTaccountFlow(map);
            if (add==0){
                flag=false;
            }
        }
        return flag;
    }

}
