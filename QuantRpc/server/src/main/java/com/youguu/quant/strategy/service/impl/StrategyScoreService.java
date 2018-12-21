package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyScoreDAO;
import com.youguu.quant.strategy.pojo.StrategyScore;
import com.youguu.quant.strategy.service.IStrategyScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Service("strategyScoreService")
public class StrategyScoreService implements IStrategyScoreService {

    @Resource
    private IStrategyScoreDAO strategyScoreDAO;

    @Override
    public int saveStrategyScore(StrategyScore strategyScore) {
        return strategyScoreDAO.saveStrategyScore(strategyScore);
    }

    @Override
    public StrategyScore getStrategyScore(int strategyId) {
        return strategyScoreDAO.getStrategyScore(strategyId);
    }
}
