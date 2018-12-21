package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyLimitUp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xyj on 2017/3/7.
 */
public class IStrategyLimitUpServiceTest extends BaseTestClass {

    IStrategyLimitUpService service = (IStrategyLimitUpService)getBean("strategyLimitUpService");

    @Test
    public void testBatchList()
    {
        List<StrategyLimitUp> list = new ArrayList<>();
        StrategyLimitUp slu  = new StrategyLimitUp();
        slu.setStockCode("1111111");
        slu.setStrategyId(3);
        slu.setCreateTime(new Date());
        list.add(slu);
        slu  = new StrategyLimitUp();
        slu.setStockCode("2222222");
        slu.setStrategyId(3);
        slu.setCreateTime(new Date());
        list.add(slu);
        service.batchList(list);
    }

    @Test
    public void testGetMax()
    {
        System.out.println(service.getMax("2017-03-05","2017-03-08"));
    }



}