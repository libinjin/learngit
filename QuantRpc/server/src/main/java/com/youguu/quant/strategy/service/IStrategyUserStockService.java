package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.StrategyUserStock;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/24.
 */
public interface IStrategyUserStockService {

    public int batchSaveStrategyUserStock(int userId, int strategyId, String stocks);

    public List<StrategyUserStock> findStockByStrategyAndUser(int userId, int strategyId);

    public List<StrategyUserStock> findStockByStrategyAndStock(int strategyId, String stockCode);

    public List<StrategyUserStock> findAllValidRelation();

    /**
     * 关注股票
     * @param userId
     * @param strategyId
     * @param stockCode
     * @return
     */
    public int saveStrategyUserStock(int userId, int strategyId, String stockCode);

    /**
     * 取消关注股票
     * @param userId
     * @param strategyId
     * @param stockCode
     * @return
     */
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode);

    /**
     * 查询关注股票数量
     * @param userId
     * @param strategyId
     * @return
     */
    public int selectFollowNum(int userId, int strategyId);

    public StrategyUserStock findStrategyUserStock(int userId, int strategyId, String stockCode);


    public String findUserFollowStock(int userId, int strategyId);
}
