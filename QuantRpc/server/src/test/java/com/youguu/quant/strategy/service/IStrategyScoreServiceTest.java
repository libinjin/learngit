package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyScore;
import org.junit.Test;

public class IStrategyScoreServiceTest extends BaseTestClass {

    IStrategyScoreService service = (IStrategyScoreService)getBean("strategyScoreService");

    @Test
    public void testSaveStrategyScore() throws Exception {
        StrategyScore ss = new StrategyScore();
        ss.setAverageProfit(1.1);
        ss.setSuccessNum(2);
        System.out.println(service.saveStrategyScore(ss));
    }

    @Test
    public void testGetStrategyScore() throws Exception {
        System.out.println(service.getStrategyScore(2235));
    }
}