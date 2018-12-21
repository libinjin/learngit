package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyUserRecordDAO;
import com.youguu.quant.strategy.pojo.StrategyUserRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Repository
public class StrategyUserRecordDAO extends QuantDAO<StrategyUserRecord> implements IStrategyUserRecordDAO {
    @Override
    public int saveStrategyUserRecord(StrategyUserRecord strategyUserRecord) {
        return insert(strategyUserRecord);
    }
}
