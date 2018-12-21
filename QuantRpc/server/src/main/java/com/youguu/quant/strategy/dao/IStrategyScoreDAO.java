package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.StrategyScore;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IStrategyScoreDAO {
    public int saveStrategyScore(StrategyScore strategyScore);

    public int deleteAll();

    public StrategyScore getStrategyScore(int strategyId);
}
