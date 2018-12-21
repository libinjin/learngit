package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyBestStock;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IStrategyBestStockDAO {

    public int saveStrategyBestStock(List<StrategyBestStock> list);

    public int updateStrategyBestStock(List<StrategyBestStock> list);

    public int clearAll(int strategyId);

    public List<StrategyBestStock> queryAll();

    public List<StrategyBestStock> queryStrategyBestStockList(int limit);

    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid,int limit);

    public List<StrategyBestStock> queryStrategyBestStockList(int strategyId, int limit);
}
