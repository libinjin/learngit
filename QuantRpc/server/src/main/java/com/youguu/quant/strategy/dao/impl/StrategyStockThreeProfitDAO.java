package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyStockThreeProfitDAO;
import com.youguu.quant.strategy.pojo.StrategyStockThreeProfit;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Repository
public class StrategyStockThreeProfitDAO extends QuantDAO<StrategyStockThreeProfit> implements IStrategyStockThreeProfitDAO {
    @Override
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfit> list) {
        return this.batchInsert(list, "batchInsertStrategyStockThreeProfit");
    }

    @Override
    public int truncateStrategyStockThreeProfit(int strategyId) {
        return this.deleteBy("truncateStrategyStockThreeProfit", strategyId);
    }

    @Override
    public List<StrategyStockThreeProfit> queryStrategyStockThreeProfit(int strategyId, String... stocks) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("stocks", StringUtils.join(stocks, ","));
        return this.findBy("queryStrategyStockThreeProfit", map);
    }

    @Override
    public StrategyStockThreeProfit findStrategyStockThreeProfit(int strategyId, String stockCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("stockCode", stockCode);
        return this.findUniqueBy("findStrategyStockThreeProfit", map);
    }
}
