package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.AverageProfit;
import com.youguu.quant.strategy.pojo.StrategyStockOneProfit;

import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/30.
 */
public interface IStrategyStockOneProfitService {
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list);

    public int truncateStrategyStockOneProfit(int strategyId);

    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks);

    /**
     * 查询一批策略追踪某证券的近一年收益
     * @param stockCode 证券代码
     * @param strategyIdList 策略ID列表
     * @return
     */
    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(String stockCode, List<Integer> strategyIdList);

    /**
     * 查询一批策略追踪某证券的近一年收益
     * @param stockCode 证券代码
     * @param strategyIdList 策略ID列表
     * @return
     */
    public Map<Integer, StrategyStockOneProfit> queryStrategyStockOneProfitMap(String stockCode, List<Integer> strategyIdList);

    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode);

    public AverageProfit getAverageProfit(int strategyId, String... stocks);

    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId);

    public Map<Integer, StrategyStockOneProfit> queryStrategyStockOneProfitMap(List<Integer> strategyIdList);

}
