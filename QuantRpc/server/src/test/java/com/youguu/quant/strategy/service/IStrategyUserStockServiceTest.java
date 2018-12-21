package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import org.junit.Test;

public class IStrategyUserStockServiceTest extends BaseTestClass {

    IStrategyUserStockService service = (IStrategyUserStockService)getBean("strategyUserStockService");

    @Test
    public void testBatchSaveStrategyUserStock() throws Exception {

        String stocks = "11600686,11600707,21000760,21000498,21000757,21000005";
        service.batchSaveStrategyUserStock(3298, 2222, stocks);
    }


    @Test
    public void testFindStockByStrategyAndUser() throws Exception {
        System.out.println(service.findStockByStrategyAndUser(111,222));
    }

    @Test
    public void testFindUserFollow(){
        String s = service.findUserFollowStock(6472086, 2222);
        System.out.println(s);
    }

    @Test
    public void testCountUserFollow(){
        int s = service.selectFollowNum(6472086, 2222);
        System.out.println(s);
    }
}