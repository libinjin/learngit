package com.youguu.quant.strategy.service;

import com.youguu.quant.strategy.pojo.StockTradeNear5days;

import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
public interface IStockTradeNear5daysService {

    int saveList(List<StockTradeNear5days> list);

    int clearData(int strategyId);

    List<StockTradeNear5days> queryData(int strategyId, long seq, int num);
}
