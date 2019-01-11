package com.aaa.ssm.controller;

import com.aaa.ssm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:BankController
 * discription:
 * author:jiasanshui
 * createTime:2019-01-07 17:27
 */
@Controller
@RequestMapping("bank")
public class BankController {

    @Autowired
    private BankService bankService;

    /**
     * 判断该用户是否绑定银行卡
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("isBind")
    public Object isBindBankCard(String userName){
        List<Map> bcList = bankService.getBankCardByUName(userName);
        if (bcList!=null&&bcList.size()>0){
            return 1;
        }
        return 0;
    }

    /**
     * 查看银行卡
     * @return
     */
    @RequestMapping("myBankCard")
    public Object getbankcard(HttpSession session, Model model){
        String userName = (String)session.getAttribute("userName");
        List<Map> bankCardList = bankService.getBankCardByUName(userName);
        for (Map map : bankCardList) {
            String bankcard = map.get("BANKCARD") + "";
            //隐藏银行卡前16位
            String s = bankcard.replace(bankcard.substring(0,15),"**** **** **** **** ");
            map.put("newBankCard",s);
        }
        model.addAttribute("bcList",bankCardList);
        return "forward:/jump/bankcard";
    }

    /**
     * 绑定银行卡
     * @return
     */
    @RequestMapping("bindBankCard")
    public Object bindBankCard(@RequestParam Map map,HttpSession session){
        //通过查询前六位是什么银行的，
        String sixBC = map.get("bankcard").toString().substring(0, 6);
        System.out.println(sixBC);
        String bankName = bankService.getBankName(sixBC);
        System.out.println(bankName);
        map.put("bankName",bankName);
        Object userName = session.getAttribute("userName");
        map.put("userName",userName);
        //绑定银行卡
        int i = bankService.bindBankCard(map);
        if(i>0){
            return "forward:/jump/index";
        }
        return "";
    }

    /**
     * 绑定银行时获取真实姓名
     * @return
     */
    @ResponseBody
    @RequestMapping("getRealName")
    public Object getRealName(@RequestParam Map map){
        String realName = bankService.getRealName(map);
        return realName;
    }

    /**
     * 解绑银行卡
     * @param BCID
     * @return
     */
    @ResponseBody
    @RequestMapping("removeBind")
    public Object removeBind(@RequestParam String BCID){
        Boolean isTrue = bankService.removeBind(BCID);
        if(isTrue){
            return 1;
        }
        return "";
    }
}
