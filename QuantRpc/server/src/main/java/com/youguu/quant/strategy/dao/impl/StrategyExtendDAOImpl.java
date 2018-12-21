package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyExtendDAO;
import com.youguu.quant.strategy.pojo.StrategyExtend;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/21.
 */
@Repository
public class StrategyExtendDAOImpl extends QuantDAO<StrategyExtend> implements IStrategyExtendDAO {
    @Override
    public int saveStrategyExtend(StrategyExtend se) {
        return super.insert(se);
    }

    @Override
    public StrategyExtend getStrategyExtend(int strategyId) {
        return super.get(strategyId);
    }

    @Override
    public int incBuy(int strategyId) {
        return super.updateBy("incBuy",strategyId);
    }

    @Override
    public int incSell(int strategyId) {
        return super.updateBy("incSell",strategyId);
    }

    @Override
    public int updatePositionNum(int strategyId, int positionNum) {
        Map<String,Object> map = new HashMap<>();
        map.put("strategyId",strategyId);
        map.put("positionNum",positionNum);
        return super.updateBy("updatePositionNum",map);
    }
}
