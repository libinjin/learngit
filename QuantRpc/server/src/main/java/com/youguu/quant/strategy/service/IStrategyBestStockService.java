package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.StrategyBestStock;
import com.youguu.quant.strategy.pojo.StrategySearch;

import java.util.List;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IStrategyBestStockService {

    public int saveStrategyBestStock(List<StrategyBestStock> list);

    public int updateStrategyBestStock(List<StrategyBestStock> list);

    public List<StrategyBestStock> queryAll();

    public List<StrategyBestStock> queryStrategyBestStockList(int strategyId, int limit);

    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid,int limit);

    public StrategySearch searchStrategyBestStock();
}
