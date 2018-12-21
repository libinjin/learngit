package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStrategyStockBlacklistDAO;
import com.youguu.quant.strategy.pojo.StrategyStockBlacklist;
import com.youguu.quant.strategy.service.IStrategyStockBlacklistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by xyj on 2016/8/30.
 */
@Service("strategyStockBlacklistService")
public class StrategyStockBlacklistService implements IStrategyStockBlacklistService {

    @Resource
    private IStrategyStockBlacklistDAO strategyStockBlacklistDAO;


    @Override
    public int saveStrategyStockBlacklist(StrategyStockBlacklist ssb) {
        String stocks = ssb.getStockCodes();
        String [] args = stocks.split(",");
        Set<String> set = new HashSet<>();
        for(String temp:args)
        {
            set.add(temp);
        }
        stocks = "";
        Iterator it =  set.iterator();
        while(it.hasNext())
        {
            stocks = stocks + it.next()+",";
        }
        stocks = stocks.substring(0,stocks.length()-1);
        ssb.setStockCodes(stocks);
        return strategyStockBlacklistDAO.saveStrategyStockBlacklist(ssb);
    }

    @Override
    public List<StrategyStockBlacklist> getStrategyStockBlacklist(int strategyId) {
        return strategyStockBlacklistDAO.getStrategyStockBlacklist(strategyId);
    }

    @Override
    public int updateStockBlack(int id, String stockCodes) {
        String [] args = stockCodes.split(",");
        Set<String> set = new HashSet<>();
        for(String temp:args)
        {
            set.add(temp);
        }
        stockCodes = "";
        Iterator it =  set.iterator();
        while(it.hasNext())
        {
            stockCodes = stockCodes + it.next()+",";
        }
        stockCodes = stockCodes.substring(0,stockCodes.length()-1);
        return strategyStockBlacklistDAO.updateStockBlack(id,stockCodes);
    }
}
