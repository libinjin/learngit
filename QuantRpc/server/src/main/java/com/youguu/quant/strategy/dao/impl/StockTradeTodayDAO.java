package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStockTradeTodayDAO;
import com.youguu.quant.strategy.pojo.StockTradeToday;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/21.
 */
@Repository
public class StockTradeTodayDAO extends QuantDAO<StockTradeToday> implements IStockTradeTodayDAO {

    @Override
    public int saveStockTradeToday(StockTradeToday stt) {
        return super.insert(stt);
    }

    @Override
    public int clearData(int strategyId) {
        return super.deleteBy("deleteByStrategyId",strategyId);
    }

    @Override
    public List<StockTradeToday> queryTodayTrade(int strategyId, int type, long seq, int num) {
        Map<String,Object> map = new HashMap<>();
        map.put("strategyId",strategyId);
        map.put("type",type);
        map.put("rank",seq==0?Long.MAX_VALUE:seq);
        map.put("limit",num);
        return super.findBy("selectByRank",map);
    }

    @Override
    public int updateStockTradeTodayProfit(List<StockTradeToday> list) {

        return super.batchUpdate(list,"batchUpdate");

    }
}
