package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyStockThreeProfit;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/30.
 */
public interface IStrategyStockThreeProfitDAO {
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfit> list);

    public int truncateStrategyStockThreeProfit(int strategyId);

    public List<StrategyStockThreeProfit> queryStrategyStockThreeProfit(int strategyId, String... stocks);

    public StrategyStockThreeProfit findStrategyStockThreeProfit(int strategyId, String stockCode);
}
