package com.youguu.quant.strategy.service.dao;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.dao.IStockTradeTodayDAO;
import com.youguu.quant.strategy.dao.IStrategyExtendDAO;
import com.youguu.quant.strategy.pojo.StockTradeToday;
import com.youguu.quant.strategy.pojo.StrategyExtend;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public class IStrategyExtendTest extends BaseTestClass {
    IStrategyExtendDAO dao = (IStrategyExtendDAO)getBean(IStrategyExtendDAO.class);
    public static StrategyExtend getStockTradeToday(){
        StrategyExtend stt = new StrategyExtend();
        stt.setStrategyId(2238);

        return stt;
    }

    @Test
    public void testSaveStrategyExtend(){
        dao.saveStrategyExtend(getStockTradeToday());
    }

    @Test
    public void incBuy(){
        dao.incBuy(2238);
    }

    @Test
    public void incSell(){
        dao.incSell(2238);
    }

    @Test
    public void updatePositionNum(){
        dao.updatePositionNum(2238,102);
    }

    @Test
    public void getStrategyExtend(){
        System.out.println(dao.getStrategyExtend(2238));
    }
}
