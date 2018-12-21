package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyStockBlacklist;

import java.util.List;

/**
 * Created by xyj on 2016/8/30.
 */
public interface IStrategyStockBlacklistDAO {

    public int saveStrategyStockBlacklist(StrategyStockBlacklist ssb);

    public List<StrategyStockBlacklist> getStrategyStockBlacklist(int strategyId);

    public int updateStockBlack(int id,String stockCodes);

}
