package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyRunLogDAO;
import com.youguu.quant.strategy.pojo.StrategyRunLog;
import com.youguu.quant.strategy.service.IStrategyRunLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xyj on 2016/8/30.
 */
@Service("strategyRunLogService")
public class StrategyRunLogService implements IStrategyRunLogService {

    @Resource
    private IStrategyRunLogDAO strategyRunLogDAO;

    @Override
    public int saveStrategyRunLog(StrategyRunLog srl) {
        return strategyRunLogDAO.saveStrategyRunLog(srl);
    }
}
