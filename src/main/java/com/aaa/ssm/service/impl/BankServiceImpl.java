package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.BankDao;
import com.aaa.ssm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:BankServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2019-01-07 17:28
 */
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankDao bankDao;

    @Override
    public List<Map> getBankCardByUName(String userName) {
        return bankDao.getBankCardByUName(userName);
    }

    @Override
    public String getRealName(Map map) {
        return bankDao.getRealName(map);
    }

    @Override
    public String getBankName(String sixBC) {
        return bankDao.getBankName(sixBC);
    }

    @Override
    public int bindBankCard(Map map) {
        return bankDao.bindBankCard(map);
    }

    @Override
    public Boolean removeBind(String BCID) {
        int i = bankDao.removeBind(BCID);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Map> getBankCards(Map map) {
        return bankDao.getBankCards(map);
    }
}
