package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.StrategyStockThreeProfit;
import org.jboss.netty.util.internal.StringUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by qxd on 2016/11/22.
 */
public class IStrategyStockThreeProfitTest  extends BaseTestClass {
    IStrategyStockThreeProfitService service = (IStrategyStockThreeProfitService)getBean("strategyStockThreeProfitService");

    @Test
    public void testGetProfit(){
        List<StrategyStockThreeProfit> list = service.queryStrategyStockThreeProfit(2235, StringUtil.split("99999999,10000300", ','));
        for(StrategyStockThreeProfit profit:list){
            System.out.println(profit.getProfit());
        }
    }
}
