package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IStrategyLimitUpDAO;
import com.youguu.quant.strategy.pojo.StrategyLimitUp;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyj on 2017/3/7.
 */
@Repository
public class StrategyLimitUpDAOImpl extends QuantDAO<StrategyLimitUp> implements IStrategyLimitUpDAO {


    @Override
    public int batchList(List<StrategyLimitUp> list) {
        return batchInsert(list,"batchInsert");
    }

    @Override
    public StrategyLimitUp getMax(String start, String end) {
        Map<String,String> map = new HashMap<>();
        map.put("start",start);
        map.put("end",end);
        return findUniqueBy("findMax",map);
    }
}
