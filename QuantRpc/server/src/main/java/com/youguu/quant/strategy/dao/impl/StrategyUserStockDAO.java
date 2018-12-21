package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyUserStockDAO;
import com.youguu.quant.strategy.pojo.StrategyUserStock;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Repository
public class StrategyUserStockDAO extends QuantDAO<StrategyUserStock> implements IStrategyUserStockDAO {
    @Override
    public int batchSaveStrategyUserStock(List<StrategyUserStock> list) {
        return batchInsert(list,"insertList");
    }

    @Override
    public int deleteStocks(int userId, int strategyId, List<String> codeList) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        map.put("codeList",codeList);
        return deleteBy("deleteStocks",map);
    }

    @Override
    public List<StrategyUserStock> findStockByStrategyAndUser(int userId, int strategyId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        return findBy("findStockByStrategyAndUser",map);
    }

    @Override
    public List<String> findStockCodeList(int userId, int strategyId) {
        Map<String,Integer> map = new HashMap<>();
        map.put("userId",userId);
        map.put("strategyId",strategyId);
        return findBy("findStockCodeList",map);
    }

    @Override
    public List<StrategyUserStock> findStockByStrategyAndStock(int strategyId, String stockCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("stockCode",stockCode);
        map.put("strategyId",strategyId);
        return this.findBy("findStockByStrategyAndStock", map);
    }

    @Override
    public List<StrategyUserStock> findAllValidRelation() {
        return this.findBy("findAllValidRelation", null);
    }

    @Override
    public int saveStrategyUserStock(int userId, int strategyId, String stockCode) {
        StrategyUserStock strategyUserStock = new StrategyUserStock();
        strategyUserStock.setUserId(userId);
        strategyUserStock.setStrategyId(strategyId);
        strategyUserStock.setStockCode(stockCode);
        strategyUserStock.setMarketId("");
        strategyUserStock.setUpdateTime(new Date());
        strategyUserStock.setCreateTime(new Date());
        return this.insert(strategyUserStock);
    }

    @Override
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("strategyId", strategyId);
        map.put("stockCode", stockCode);
        return this.deleteBy("deleteUserStock", map);
    }

    @Override
    public int selectFollowNum(int userId, int strategyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("strategyId", strategyId);
        return this.findUniqueBy("countFollowNum", map);
    }

    @Override
    public StrategyUserStock findStrategyUserStock(int userId, int strategyId, String stockCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("strategyId", strategyId);
        map.put("stockCode", stockCode);
        return this.findUniqueBy("findStrategyUserStock", map);
    }

    @Override
    public List<String> findUserFollowStock(int userId, int strategyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("strategyId", strategyId);
        return this.findBy("findUserFollowStock", map);
    }
}
