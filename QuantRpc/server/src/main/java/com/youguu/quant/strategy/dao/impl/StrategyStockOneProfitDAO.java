package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyStockOneProfitDAO;
import com.youguu.quant.strategy.pojo.StrategyStockOneProfit;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Repository
public class StrategyStockOneProfitDAO extends QuantDAO<StrategyStockOneProfit> implements IStrategyStockOneProfitDAO {

    @Override
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list) {
        return this.batchInsert(list, "batchInsertStrategyStockOneProfit");
    }

    @Override
    public int truncateStrategyStockOneProfit(int strategyId) {
        return this.deleteBy("truncateStrategyStockOneProfit", strategyId);
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("stocks", StringUtils.join(stocks, ","));
        return this.findBy("queryStrategyStockOneProfit", map);
    }

    @Override
    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("stockCode", stockCode);
        return this.findUniqueBy("findStrategyStockOneProfit", map);
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(String stockCode, List<Integer> strategyIdList) {
        Map<String, Object> map = new HashMap<>();
        map.put("stockCode", stockCode);
        map.put("strategyIdList", strategyIdList);
        return this.findBy("queryStrategyStockOneProfitList", map);
    }

    @Override
    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId) {
        return this.findUniqueBy("findStrategyStockOneProfitByStrategyId", strategyId);
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(List<Integer> strategyIdList) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyIdList", strategyIdList);
        return this.findBy("queryStrategyStockOneProfitListByStrategyId", map);
    }
}
