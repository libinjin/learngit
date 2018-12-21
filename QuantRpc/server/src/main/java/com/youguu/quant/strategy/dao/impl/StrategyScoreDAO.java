package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyScoreDAO;
import com.youguu.quant.strategy.pojo.StrategyScore;
import org.springframework.stereotype.Repository;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Repository
public class StrategyScoreDAO extends QuantDAO<StrategyScore> implements IStrategyScoreDAO {
    @Override
    public int saveStrategyScore(StrategyScore strategyScore) {
        int strategyId = strategyScore.getStrategyId();
        this.deleteBy("deleteById", strategyId);
        return insert(strategyScore);
    }

    @Override
    public int deleteAll() {
        return this.deleteBy("deleteAll",null);
    }

    @Override
    public StrategyScore getStrategyScore(int strategyId) {
        return this.findUniqueBy("getStrategyScore",strategyId);
    }
}
