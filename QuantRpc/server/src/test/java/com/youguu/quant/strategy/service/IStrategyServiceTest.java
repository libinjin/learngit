package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.Strategy;
import org.junit.Test;

import java.util.Date;

public class IStrategyServiceTest extends BaseTestClass {

    IStrategyService service = (IStrategyService)getBean("strategyService");

    @Test
    public void testSaveStrategy() throws Exception {
        Strategy s = new Strategy();
        s.setName("abc");
        s.setLogo("2");
        s.setDescription("3");
        s.setType(4);
        s.setRatingLabel("6");
        s.setDisplayStatus(8);
        s.setCreateTime(new Date());
        s.setRemark("8");
        s.setClassName("9");
        s.setRunStatus("10");
        System.out.println(service.saveStrategy(s));
        System.out.println(s.getId());
    }

    @Test
    public void testDeleteStrategy() throws Exception {
        System.out.println(service.deleteStrategy(1));
    }

    @Test
    public void testUpdateStrategy() throws Exception {
        Strategy s = new Strategy();
        s.setId(2);
        s.setName("2");
        s.setLogo("2");
        s.setDescription("2");
        s.setType(4);
        s.setRatingLabel("6");
        s.setCreateTime(new Date());
        s.setRemark("8");
        System.out.println(service.updateStrategy(s));
    }

    @Test
    public void testGetStrategy() throws Exception {
        System.out.println(service.getStrategy(2));
    }

    @Test
    public void testQueryStrategyByPage() throws Exception {
        System.out.println(service.queryStrategyByPage(null,1,20));
    }

    @Test
    public void testUpdateStrategyStatus() throws Exception {
        System.out.println(service.updateStrategyStatus(2,0));
    }

    @Test
    public void testFindAll() throws Exception {
        System.out.println(service.findAll());
    }

    @Test
    public void testFindStrategyByCategoryId() throws Exception {
        System.out.println(service.findStrategyByCategoryId("7"));
    }

    @Test
    public void testFindAllRunStrategyId() throws Exception {
        System.out.println(service.findAllRunStrategyId());
    }
}