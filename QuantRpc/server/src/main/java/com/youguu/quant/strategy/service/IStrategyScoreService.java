package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.StrategyScore;

/**
 * Created by SomeBody on 2016/8/25.
 * 全部策略平均指标
 */
public interface IStrategyScoreService {
    public int saveStrategyScore(StrategyScore strategyScore);

    public StrategyScore getStrategyScore(int strategyId);
}
