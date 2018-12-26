package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:BiaodeService
 * discription:
 * author:fhm
 * createTime:2018-12-15 10:01
 */
public interface BiaodeService {
    /**
     * 用户借款标的列表查询 (信用贷款 待审核)
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 用户借款标的列表查询 (抵押贷款 待审核)
     * @return
     */
    List<Map> getListOne(Map map);

    /**
     * 审核通过，更新用户信息表审核状态,招标开始时间
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 初审，更新用户借款表审核状态
     * @param map
     * @return
     */
    int edit(Map map);

    /**
     * 满标一审操作
     * @param map
     * @return
     */
    int fkCheck(Map map);

    /**
     * 查询投标详情
     * @param borrownum
     * @return
     */
    List<Map> getTenderinfo(String borrownum);

    /**
     * 招标中的标的分页列表
     * @param map
     * @return
     */
    List<Map> getPage(Map map);

    /**
     * 获取待放款的投标信息
     * @param map
     * @return
     */
    List<Map> getPageByLoan(Map map);

    /**
     * 更改标的状态
     * @param map
     * @return
     */
    int updateBidState(Map map);

    /**
     * 根据标的编号查询历史审核信息
     * @param borrownum
     * @return
     */
    Map getAduitBid(String borrownum);
}
