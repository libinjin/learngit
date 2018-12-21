package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyComment;
import org.junit.Test;

public class IStrategyCommentServiceTest extends BaseTestClass {

    IStrategyCommentService service = (IStrategyCommentService)getBean("strategyCommentService");

    @Test
    public void testSaveStrategyComment() throws Exception {
        StrategyComment sc = new StrategyComment();
        sc.setStrategyId(1);
        sc.setUserId(2);
        sc.setCotent("3");
        System.out.println(service.saveStrategyComment(sc));
    }

    @Test
    public void testDeleteStrategyComment() throws Exception {
        System.out.println(service.deleteStrategyComment(1));
    }

    @Test
    public void testUpdateStrategyComment() throws Exception {
        StrategyComment sc = new StrategyComment();
        sc.setId(1);
        sc.setStrategyId(1);
        sc.setUserId(1);
        sc.setCotent("1");
        System.out.println(service.updateStrategyComment(sc));
    }

    @Test
    public void testGetStrategyComment() throws Exception {
        System.out.println(service.getStrategyComment(1));
    }

    @Test
    public void testQueryStrategyCommentByPage() throws Exception {
        System.out.println(service.queryStrategyCommentByPage(null,1,20));
    }
}