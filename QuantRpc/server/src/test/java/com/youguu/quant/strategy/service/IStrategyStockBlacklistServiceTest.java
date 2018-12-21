package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyStockBlacklist;
import org.junit.Test;

public class IStrategyStockBlacklistServiceTest extends BaseTestClass {

    IStrategyStockBlacklistService service = (IStrategyStockBlacklistService)getBean("strategyStockBlacklistService");

    @Test
    public void testSaveStrategyStockBlacklist() throws Exception {
        StrategyStockBlacklist ssb = new StrategyStockBlacklist();
        ssb.setStockCodes("21002807,11600005,11600004");
        ssb.setStrategyId(2222);
        System.out.println(service.saveStrategyStockBlacklist(ssb));
    }

    @Test
    public void testGetStrategyStockBlacklist() throws Exception {
        System.out.println(service.getStrategyStockBlacklist(1));
    }
}