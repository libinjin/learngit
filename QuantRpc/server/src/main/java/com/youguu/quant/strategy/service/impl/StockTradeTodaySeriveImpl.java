package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStockTradeTodayDAO;
import com.youguu.quant.strategy.dao.IStrategyExtendDAO;
import com.youguu.quant.strategy.pojo.StockTradeToday;
import com.youguu.quant.strategy.service.IStockTradeTodaySerive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
@Service("stockTradeTodaySerive")
public class StockTradeTodaySeriveImpl implements IStockTradeTodaySerive {

    @Resource
    private IStockTradeTodayDAO stockTradeTodayDAO;

    @Resource
    private IStrategyExtendDAO strategyExtendDAO;

    @Transactional("quantTradeTx")
    @Override
    public int saveStockTradeToday(StockTradeToday stt) {
        int update = stockTradeTodayDAO.saveStockTradeToday(stt);
        if(stt.getType()==StockTradeToday.TYPE_BUY){
            strategyExtendDAO.incBuy(stt.getStrategyId());
        }else if(stt.getType()==StockTradeToday.TYPE_SELL){
            strategyExtendDAO.incSell(stt.getStrategyId());
        }

        return update;
    }

    @Transactional("quantTradeTx")
    @Override
    public int clearData(int strategyId,int positionNum) {
        int result = stockTradeTodayDAO.clearData(strategyId);
        strategyExtendDAO.updatePositionNum(strategyId,positionNum);
        return result;
    }

    @Override
    public int updateStockTradeTodayProfit(List<StockTradeToday> list) {
        return stockTradeTodayDAO.updateStockTradeTodayProfit(list);
    }

    @Override
    public List<StockTradeToday> queryTodayTrade(int strategyId, int type, long seq, int num) {
        return stockTradeTodayDAO.queryTodayTrade(strategyId,type,seq,num);
    }
}
