package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StockTradeToday;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public class IStockTradeTodayServiceTest extends BaseTestClass {
    IStockTradeTodaySerive server = (IStockTradeTodaySerive)getBean(IStockTradeTodaySerive.class);
    public static StockTradeToday getStockTradeToday(){
        StockTradeToday stt = new StockTradeToday();
        stt.setStrategyId(2238);
        stt.setType(1);
        stt.setStockCode("21000002");
        stt.setStockName("万科 A");
        stt.setTradeTime(201611211002L);
        stt.setPrice(12);
        stt.setRank(1001000002);
        stt.setCreateTime(new Date());
        return stt;
    }

    @Test
    public void testSaveStockTradeToday(){
        StockTradeToday stt = IStockTradeTodayServiceTest.getStockTradeToday();
        System.out.println(server.saveStockTradeToday(stt));
    }

    @Test
    public void testClearData(){
        System.out.println(server.clearData(2231,22));
    }

    @Test
    public void testQueryTodayTrade(){
        System.out.println(server.queryTodayTrade(2238, 1, 1001000002L, 10));
    }

    @Test
    public void updateStockTradeTodayProfit(){
        List<StockTradeToday> list = new ArrayList<>();
        StockTradeToday stt = new StockTradeToday();
        stt.setId(2);
        stt.setRank(9089000001L);
        list.add(stt);

        stt = new StockTradeToday();
        stt.setId(3);
        stt.setRank(9089000002L);
        list.add(stt);

        System.out.println(server.updateStockTradeTodayProfit(list));
    }
}
