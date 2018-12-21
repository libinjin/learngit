package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.strategy.dao.IStockTradeNear5daysDAO;
import com.youguu.quant.strategy.pojo.StockTradeNear5days;
import com.youguu.quant.strategy.service.IStockTradeNear5daysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2016/11/21.
 */
@Service("stockTradeNear5daysService")
public class StockTradeNear5daysServiceImpl implements IStockTradeNear5daysService {

    @Resource
    private IStockTradeNear5daysDAO stockTradeNear5daysDAO ;

    @Override
    public int saveList(List<StockTradeNear5days> list) {
        return stockTradeNear5daysDAO.saveList(list);
    }

    @Override
    public int clearData(int strategyId) {
        return stockTradeNear5daysDAO.clearData(strategyId);
    }

    @Override
    public List<StockTradeNear5days> queryData(int strategyId, long seq, int num) {
        return stockTradeNear5daysDAO.queryData(strategyId,seq,num);
    }
}
