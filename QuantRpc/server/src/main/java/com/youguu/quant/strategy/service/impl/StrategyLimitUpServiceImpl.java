package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyLimitUpDAO;
import com.youguu.quant.strategy.pojo.StrategyLimitUp;
import com.youguu.quant.strategy.service.IStrategyLimitUpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xyj on 2017/3/7.
 */
@Service("strategyLimitUpService")
public class StrategyLimitUpServiceImpl implements IStrategyLimitUpService {

    @Resource
    private IStrategyLimitUpDAO strategyLimitUpDAO;

    @Override
    public int batchList(List<StrategyLimitUp> list) {
        return strategyLimitUpDAO.batchList(list);
    }

    @Override
    public StrategyLimitUp getMax(String start, String end) {
        return strategyLimitUpDAO.getMax(start, end);
    }
}
