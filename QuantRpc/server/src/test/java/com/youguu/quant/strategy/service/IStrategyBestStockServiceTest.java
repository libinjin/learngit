package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyBestStock;
import com.youguu.quant.strategy.pojo.StrategySearch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IStrategyBestStockServiceTest extends BaseTestClass {

    IStrategyBestStockService service = (IStrategyBestStockService)getBean("strategyBestStockService");

    @Test
    public void testSaveStrategyBestStock() throws Exception {
        StrategyBestStock s1 = new StrategyBestStock();
        StrategyBestStock s2 = new StrategyBestStock();
        s1.setStrategyId(1);
        s1.setStockCode("11111111111111111111111111111111111111111111111");
        s1.setStockName("1");
        s1.setBuyPrice(112233);
        s2.setStrategyId(2);
        s2.setStockCode("2");
        s2.setStockName("2");
        s2.setBuyPrice(112200);
        List<StrategyBestStock> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        System.out.println(service.saveStrategyBestStock(list));
    }

    @Test
    public void testQueryAll() throws Exception {
        System.out.println(service.queryStrategyBestStockList(2235, 10));
    }

    @Test
    public void testuopdateStrategyBestStock() throws Exception {
        StrategyBestStock s1 = new StrategyBestStock();
        StrategyBestStock s2 = new StrategyBestStock();
        s1.setStrategyId(2237);
        s1.setStockCode("11603031");
        s1.setRank(3000.00);
        s2.setStrategyId(2237);
        s2.setStockCode("11600222");
        s2.setRank(4000.00);
        List<StrategyBestStock> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        System.out.println(service.updateStrategyBestStock(list));
    }

    @Test
    public void testGetSearch(){
        StrategySearch search = service.searchStrategyBestStock();
        System.out.println(search.getList());
    }
}