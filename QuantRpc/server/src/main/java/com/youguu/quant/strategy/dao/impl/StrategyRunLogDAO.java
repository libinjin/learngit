package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyRunLogDAO;
import com.youguu.quant.strategy.pojo.StrategyRunLog;
import org.springframework.stereotype.Repository;

/**
 * Created by xyj on 2016/8/30.
 */
@Repository
public class StrategyRunLogDAO extends QuantDAO<StrategyRunLog> implements IStrategyRunLogDAO {
    @Override
    public int saveStrategyRunLog(StrategyRunLog srl) {
        return insert(srl);
    }
}
