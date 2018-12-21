package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyProductDAO;
import com.youguu.quant.strategy.pojo.StrategyProduct;
import com.youguu.quant.strategy.service.IStrategyProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xyj on 2016/8/30.
 */
@Service("strategyProductService")
public class StrategyProductService implements IStrategyProductService {

    @Resource
    private IStrategyProductDAO strategyProductDAO;

    @Override
    public int saveStrategyProduct(StrategyProduct sp) {
        return strategyProductDAO.saveStrategyProduct(sp);
    }
}
