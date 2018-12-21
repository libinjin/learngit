package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyStockBlacklistDAO;
import com.youguu.quant.strategy.pojo.StrategyStockBlacklist;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyj on 2016/8/30.
 */
@Repository
public class StrategyStockBlacklistDAO extends QuantDAO<StrategyStockBlacklist> implements IStrategyStockBlacklistDAO {
    @Override
    public int saveStrategyStockBlacklist(StrategyStockBlacklist ssb) {
        return insert(ssb);
    }

    @Override
    public List<StrategyStockBlacklist> getStrategyStockBlacklist(int strategyId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("strategyId",strategyId);
        return findBy("getStrategyStockBlacklist",map);
    }

    @Override
    public int updateStockBlack(int id, String stockCodes) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("stockCodes",stockCodes);
        map.put("updateTime",new Date());
        return updateBy("updateData",map);
    }
}
