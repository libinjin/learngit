package com.youguu.quant.strategy.service.impl;

import com.youguu.cache.config.CacheAnnotation;
import com.youguu.core.zookeeper.pro.ZkPropertiesHelper;
import com.youguu.quant.strategy.dao.IStrategyBestStockDAO;
import com.youguu.quant.strategy.dao.IStrategyDAO;
import com.youguu.quant.strategy.pojo.Strategy;
import com.youguu.quant.strategy.pojo.StrategyBestStock;
import com.youguu.quant.strategy.pojo.StrategySearch;
import com.youguu.quant.strategy.service.IStrategyBestStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Service("strategyBestStockService")
public class StrategyBestStockService implements IStrategyBestStockService {

    @Resource
    private IStrategyDAO strategyDAO;
    @Resource
    private IStrategyBestStockDAO strategyBestStockDAO;

    @Transactional
    @Override
    public int saveStrategyBestStock(List<StrategyBestStock> list) {
        if(list!=null && list.size()>0){
            strategyBestStockDAO.clearAll(list.get(0).getStrategyId());
        }
        return strategyBestStockDAO.saveStrategyBestStock(list);
    }

    @Override
    public List<StrategyBestStock> queryAll() {
        return strategyBestStockDAO.queryAll();
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockList(int strategyId, int limit) {
        return strategyBestStockDAO.queryStrategyBestStockList(strategyId, limit);
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid, int limit) {
        return strategyBestStockDAO.queryStrategyBestStockListBySid(sid, limit);
    }

    @CacheAnnotation(poolName = "user", field = CacheAnnotation.FIELD_AUTO ,expire = 60*5)
    @Override
    public StrategySearch searchStrategyBestStock() {
        int num = 5;
        StrategySearch search = new StrategySearch();
        //获取配置文件
        Properties pro = ZkPropertiesHelper.getCacheAndWatchProperties("config.properties", true);
        search.setTitleLeft(pro.getProperty("titleleft"));
        search.setTitleRight(pro.getProperty("titleright"));
        search.setTitleRightUrl(pro.getProperty("titlerighturl"));
        //所有运行机器人
        List<Strategy> strategyList = strategyDAO.findAll();
        //机器人最优股票
        if(strategyList!=null && strategyList.size()>0){
            List<StrategyBestStock> bestStocks = new ArrayList<>();
            List<StrategyBestStock> stocks;
            BigDecimal b;
            for(Strategy strategy:strategyList){
                stocks = strategyBestStockDAO.queryStrategyBestStockList(strategy.getId(), num);
                for(StrategyBestStock stock:stocks){
                    stock.setStrategyName(strategy.getName());
                    stock.setLogo(strategy.getLogo());
                    b = new BigDecimal(stock.getProfit()*100);
                    stock.setProfitStr(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"%");
                }
                bestStocks.addAll(stocks);
            }
            search.setList(bestStocks);
        }

        return search;
    }

    public int updateStrategyBestStock(List<StrategyBestStock> list){
        return strategyBestStockDAO.updateStrategyBestStock(list);
    }
}
