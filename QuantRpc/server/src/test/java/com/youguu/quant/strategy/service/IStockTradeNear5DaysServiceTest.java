package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StockTradeNear5days;
import com.youguu.quant.strategy.pojo.StockTradeToday;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public class IStockTradeNear5DaysServiceTest extends BaseTestClass {

    IStockTradeNear5daysService server = (IStockTradeNear5daysService)getBean(IStockTradeNear5daysService.class);

    public static List<StockTradeNear5days> getStockTradeNear5days(){
        List<StockTradeNear5days> list = new ArrayList<>();
        StockTradeNear5days stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("21000002");
        stt.setStockName("万科 A");
        stt.setTradeTime(201611211002L);
        stt.setPrice(12);
        stt.setRank(1001000002);
        stt.setCreateTime(new Date());
        list.add(stt);

        stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("21000001");
        stt.setStockName("平安银行");
        stt.setTradeTime(201611211002L);
        stt.setPrice(12);
        stt.setRank(1001000001);
        stt.setCreateTime(new Date());
        list.add(stt);

        stt = new StockTradeNear5days();
        stt.setStrategyId(2238);
        stt.setStockCode("11600000");
        stt.setStockName("浦发银行");
        stt.setTradeTime(201611211002L);
        stt.setPrice(12);
        stt.setRank(1001600000);
        stt.setCreateTime(new Date());
        list.add(stt);


        return list;
    }

    @Test
    public void testClearData(){
        System.out.println(server.clearData(2238));
    }

    @Test
    public void saveList(){
        System.out.println(server.saveList(getStockTradeNear5days()));
    }

    @Test
    public void queryData(){
        System.out.println(server.queryData(2238,1001600000L,2));
    }
}
