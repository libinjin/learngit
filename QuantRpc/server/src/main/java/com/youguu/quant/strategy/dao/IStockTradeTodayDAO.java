package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StockTradeToday;

import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public interface IStockTradeTodayDAO {

    /**
     * 新增
     * @param stt
     * @return
     */
    int saveStockTradeToday(StockTradeToday stt);

    /**
     * 数据清零
     * @param strategyId
     * @return
     */
    int clearData(int strategyId);

    /**
     * 查询指定策略今日操作
     * @param strategyId
     * @param type
     * @param seq
     * @param num
     * @return
     */
    List<StockTradeToday> queryTodayTrade(int strategyId,int type,long seq , int num);

    /**
     * 修改盈利率
     * @param list
     * @return
     */
    int updateStockTradeTodayProfit(List<StockTradeToday> list);

}
