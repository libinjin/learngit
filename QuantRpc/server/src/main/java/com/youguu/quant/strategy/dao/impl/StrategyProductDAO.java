package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyProductDAO;
import com.youguu.quant.strategy.pojo.StrategyProduct;
import org.springframework.stereotype.Repository;

/**
 * Created by xyj on 2016/8/30.
 */
@Repository
public class StrategyProductDAO extends QuantDAO<StrategyProduct> implements IStrategyProductDAO {


    @Override
    public int saveStrategyProduct(StrategyProduct sp) {
        return insert(sp);
    }
}
