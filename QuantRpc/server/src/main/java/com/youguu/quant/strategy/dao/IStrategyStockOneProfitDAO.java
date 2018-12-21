package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyStockOneProfit;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/30.
 */
public interface IStrategyStockOneProfitDAO {
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list);

    public int truncateStrategyStockOneProfit(int strategyId);

    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks);

    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode);

    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(String stockCode, List<Integer> strategyIdList);

    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId);

    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(List<Integer> strategyIdList);

}
