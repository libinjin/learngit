package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyExtendDAO;
import com.youguu.quant.strategy.pojo.StrategyExtend;
import com.youguu.quant.strategy.service.IStrategyExtendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by leo on 2016/11/22.
 */
@Service("strategyExtendService")
public class StrategyExtendService implements IStrategyExtendService {
    @Resource
    private IStrategyExtendDAO strategyExtendDAO;

    @Override
    public StrategyExtend getStrategyExtend(int strategyId) {
        return strategyExtendDAO.getStrategyExtend(strategyId);
    }
}
