package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStockTradeNear5daysDAO;
import com.youguu.quant.strategy.pojo.StockTradeNear5days;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/21.
 */
@Repository
public class StockTradeNear5daysDAOImpl extends QuantDAO<StockTradeNear5days> implements IStockTradeNear5daysDAO {
    @Override
    public int saveList(List<StockTradeNear5days> list) {
        return super.batchInsert(list,"insertList");
    }

    @Override
    public int clearData(int strategyId) {
        return super.deleteBy("deleteByStrategyId",strategyId);
    }

    @Override
    public List<StockTradeNear5days> queryData(int strategyId, long seq, int num) {
        Map<String,Object> map = new HashMap<>();
        map.put("strategyId",strategyId);
        map.put("rank",seq==0?Long.MAX_VALUE:seq);
        map.put("limit",num);
        return super.findBy("selectByRank",map);
    }
}
