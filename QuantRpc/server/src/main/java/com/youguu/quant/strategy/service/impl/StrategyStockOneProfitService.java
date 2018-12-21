package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.strategy.dao.IStrategyStockOneProfitDAO;
import com.youguu.quant.strategy.pojo.AverageProfit;
import com.youguu.quant.strategy.pojo.StrategyStockOneProfit;
import com.youguu.quant.strategy.service.IStrategyStockOneProfitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("strategyStockOneProfitService")
public class StrategyStockOneProfitService implements IStrategyStockOneProfitService {

    @Resource
    private IStrategyStockOneProfitDAO strategyStockOneProfitDAO;

    @Override
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list) {
        return strategyStockOneProfitDAO.batchInsertStrategyStockOneProfit(list);
    }

    @Override
    public int truncateStrategyStockOneProfit(int strategyId) {
        return strategyStockOneProfitDAO.truncateStrategyStockOneProfit(strategyId);
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks) {
        return strategyStockOneProfitDAO.queryStrategyStockOneProfit(strategyId, stocks);
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfitList(String stockCode, List<Integer> strategyIdList) {
        return strategyStockOneProfitDAO.queryStrategyStockOneProfitList(stockCode, strategyIdList);
    }

    @Override
    public Map<Integer, StrategyStockOneProfit> queryStrategyStockOneProfitMap(String stockCode, List<Integer> strategyIdList) {
        List<StrategyStockOneProfit> list = queryStrategyStockOneProfitList(stockCode, strategyIdList);
        Map<Integer, StrategyStockOneProfit> map = new HashMap<>();
        if(list!=null && list.size()>0){
            for(StrategyStockOneProfit oneProfit : list){
                map.put(oneProfit.getStrategyId(), oneProfit);
            }
        }
        return map;
    }

    @Override
    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode) {
        return strategyStockOneProfitDAO.findStrategyStockOneProfit(strategyId, stockCode);
    }

    @Override
    public AverageProfit getAverageProfit(int strategyId, String... stocks) {
        List<StrategyStockOneProfit> list = this.queryStrategyStockOneProfit(strategyId, stocks);
        double totalProfit = 0;
        if (list != null && list.size() > 0) {
            for (StrategyStockOneProfit strategyStockOneProfit : list) {
                totalProfit += strategyStockOneProfit.getProfit();
            }
        }
        double average = (totalProfit - stocks.length) / stocks.length;

        AverageProfit averageProfit = new AverageProfit();
        averageProfit.setProfit(average);

        StrategyStockOneProfit strategyStockOneProfit = strategyStockOneProfitDAO.findStrategyStockOneProfit(strategyId, TradeSignal.HS300);
        averageProfit.setOvertop(average - strategyStockOneProfit.getProfit());
        return averageProfit;
    }

    @Override
    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId) {
        return strategyStockOneProfitDAO.findStrategyStockOneProfit(strategyId);
    }

    @Override
    public Map<Integer, StrategyStockOneProfit> queryStrategyStockOneProfitMap(List<Integer> strategyIdList) {
        List<StrategyStockOneProfit> list = strategyStockOneProfitDAO.queryStrategyStockOneProfitList(strategyIdList);
        Map<Integer, StrategyStockOneProfit> map = new HashMap<>();
        if(list!=null && list.size()>0){
            for(StrategyStockOneProfit oneProfit : list){
                map.put(oneProfit.getStrategyId(), oneProfit);
            }
        }
        return map;
    }
}
