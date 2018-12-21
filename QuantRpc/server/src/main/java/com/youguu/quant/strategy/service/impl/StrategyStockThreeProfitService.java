package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.strategy.dao.IStrategyStockThreeProfitDAO;
import com.youguu.quant.strategy.pojo.AverageProfit;
import com.youguu.quant.strategy.pojo.StrategyStockThreeProfit;
import com.youguu.quant.strategy.service.IStrategyStockThreeProfitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SomeBody on 2016/8/30.
 */
@Service("strategyStockThreeProfitService")
public class StrategyStockThreeProfitService implements IStrategyStockThreeProfitService {

    @Resource
    private IStrategyStockThreeProfitDAO strategyStockThreeProfitDAO;

    @Override
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfit> list) {
        return strategyStockThreeProfitDAO.batchInsertStrategyStockThreeProfit(list);
    }

    @Override
    public int truncateStrategyStockThreeProfit(int strategyId) {
        return strategyStockThreeProfitDAO.truncateStrategyStockThreeProfit(strategyId);
    }

    @Override
    public List<StrategyStockThreeProfit> queryStrategyStockThreeProfit(int strategyId, String... stocks) {
        return strategyStockThreeProfitDAO.queryStrategyStockThreeProfit(strategyId, stocks);
    }

    @Override
    public AverageProfit getAverageProfit(int strategyId, String... stocks) {
        List<StrategyStockThreeProfit> list = this.queryStrategyStockThreeProfit(strategyId, stocks);
        double totalProfit = 0;
        if(list!=null && list.size()>0){
            for(StrategyStockThreeProfit strategyStockThreeProfit : list){
                totalProfit += strategyStockThreeProfit.getProfit();
            }
        }
        double average = (totalProfit-stocks.length)/stocks.length;

        AverageProfit averageProfit = new AverageProfit();
        averageProfit.setProfit(average);

        StrategyStockThreeProfit strategyStockThreeProfit = strategyStockThreeProfitDAO.findStrategyStockThreeProfit(strategyId, TradeSignal.HS300);
        averageProfit.setOvertop(average-strategyStockThreeProfit.getProfit());
        return averageProfit;
    }
}
