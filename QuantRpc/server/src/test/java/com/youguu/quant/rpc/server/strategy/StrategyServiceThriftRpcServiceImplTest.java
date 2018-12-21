package com.youguu.quant.rpc.server.strategy;

import com.youguu.quant.base.BaseTestClass;
import org.apache.thrift.TException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StrategyServiceThriftRpcServiceImplTest extends BaseTestClass {
    StrategyServiceThriftRpcServiceImpl service = (StrategyServiceThriftRpcServiceImpl) getBean("strategyServiceThriftRpcService");

    @Test
    public void testFindStockByStrategyAndStock() throws Exception {
        String json = service.findStockByStrategyAndStock(1111, "21000002");
        System.out.println(json);
    }

    @Test
    public void testQueryStrategyBestStock()throws TException {
        System.out.println(service.queryStrategyBestStock(100));
    }

    @Test
    public void testQueryAllStrategy() throws TException {
        String json = service.queryAllStrategy(3298);
        System.out.println(json);
    }

    @Test
    public void testQueryStrategyReport() throws TException {
        String json = service.queryStrategyReport(1111, "21000002,21002751,21000909", 3298);
        System.out.println(json);
    }

    @Test
    public void testQeryAllStrategyBestStock() throws TException {
        String json = service.queryAllStrategyBestStock(2235);
        System.out.println(json);
    }

    @Test
    public void testFindStockByStrategyAndUser() throws TException {
        String json = service.findStockByStrategyAndUser(3298, 1111);
        System.out.println(json);
    }

    @Test
    public void testFindStrategyByUserId() throws TException {
        String json = service.findStrategyByUserId(18, 1);
        System.out.println(json);
    }

    @Test
    public void testGetRecommendHotStockList(){
        try {
            String json = service.getRecommendHotStockList(0,2);
            System.out.println(json);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsExitsFile(){
        try {
            boolean result = service.isExitsFile(2222);
            System.out.println("ddddddddddddddd="+result);
        } catch (TException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testClearStrategyData(){
        try {
            boolean result = service.clearStrategyData(2222);
            System.out.println(result);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList(){
        List ls = new ArrayList();
        ls.add("000200");
        ls.add("000300");
        ls.add("000400");

        List ls2 = new ArrayList();
        ls2.add("000200");
        ls2.add("000500");
        ls2.add("000400");

        List list = new ArrayList(Arrays.asList(new Object[ls2.size()]));
        Collections.copy(list, ls2);
        list.removeAll(ls);
        System.out.println(list);
    }

    @Test
    public void testBatchSaveStrategyUserStock() throws TException {
        service.batchSaveStrategyUserStock(3298, 2222, "11600686,11600707,21000760,11601611,11600149");
    }


    @Test
    public void testQueryStrategyPortfolioList() throws TException {
        String json = service.queryStrategyPortfolioList(3298, 2235, 2);
        System.out.println(json);
    }

    @Test
    public void testForecastPortfolioList() throws TException {
        String json = service.forecastPortfolioList(32, 2235);
        System.out.println(json);
    }
}