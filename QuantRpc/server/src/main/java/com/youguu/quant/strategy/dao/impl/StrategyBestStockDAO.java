package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyBestStockDAO;
import com.youguu.quant.strategy.pojo.StrategyBestStock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Repository
public class StrategyBestStockDAO extends QuantDAO<StrategyBestStock> implements IStrategyBestStockDAO {
    @Override
    public int saveStrategyBestStock(List<StrategyBestStock> list) {
        return batchInsert(list,"insertList");
    }

    @Override
    public int clearAll(int strategyId) {
        return this.deleteBy("clear", strategyId);
    }

    @Override
    public List<StrategyBestStock> queryAll() {
        return findBy("queryAll",null);
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockList(int limit) {
        return this.findBy("queryStrategyBestStockList", limit);
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockList(int strategyId,int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("limit", limit);

        return this.findBy("queryStrategyBestStockList", map);
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid, int limit) {
        Map<String,Integer> map = new HashMap<>();
        map.put("strategyId",sid);
        map.put("limit",limit);
        return findBy("queryStrategyBestStockListBySid",map);
    }

    public int updateStrategyBestStock(List<StrategyBestStock> list){
        return batchUpdate(list,"batchUpdate");
    }
}
