package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyRunLog;
import org.junit.Test;

import java.util.Date;

public class IStrategyRunLogServiceTest extends BaseTestClass {

    IStrategyRunLogService service = (IStrategyRunLogService)getBean("strategyRunLogService");

    @Test
    public void testSaveStrategyRunLog() throws Exception {
        StrategyRunLog srl = new StrategyRunLog();
        srl.setStatus(1);
        srl.setStrategyId(1);
        srl.setCreateTime(new Date());
        System.out.println(service.saveStrategyRunLog(srl));
    }
}