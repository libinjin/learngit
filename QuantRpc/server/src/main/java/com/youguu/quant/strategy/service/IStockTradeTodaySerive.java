package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.StockTradeToday;

import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public interface IStockTradeTodaySerive {

    int saveStockTradeToday(StockTradeToday stt);

    int clearData(int strategyId,int positionNum);

    int updateStockTradeTodayProfit(List<StockTradeToday> list);

    List<StockTradeToday> queryTodayTrade(int strategyId,int type,long seq , int num);


}
