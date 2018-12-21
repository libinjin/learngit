package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyUser;
import org.junit.Test;

import java.util.Date;

public class IStrategyUserServiceTest extends BaseTestClass {

    IStrategyUserService service = (IStrategyUserService)getBean("strategyUserService");

    @Test
    public void testSaveStrategyUser() throws Exception {
        StrategyUser su = new StrategyUser();
        su.setUserId(123);
        su.setStrategyId(111);
        su.setExpireTime(new Date());
        su.setUpdateTime(new Date());
        su.setCreateTime(new Date());
        System.out.println(service.saveStrategyUser(su));
    }

    @Test
    public void testExpandExpireTime() throws Exception {
        System.out.println(service.expandExpireTime(123,111,2,"",0.1));
    }

    @Test
    public void testGetStrategyUser() throws Exception {
        System.out.println(service.getStrategyUser(1));
    }

    @Test
    public void testFindStrategyByUserId() throws Exception {
        System.out.println(service.findStrategyByUserId(123,1));
    }

    @Test
    public void testQueryStrategyUserByPage() throws Exception {
        System.out.println(service.queryStrategyUserByPage(null,1,10));
    }

    @Test
    public void testFindStrategyBuyUserCount() throws Exception {
        System.out.println(service.findStrategyBuyUserCount("2016-11-01"));
    }
}