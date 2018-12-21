package com.youguu.quant.strategy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.jms.handler.IMqHandler;
import com.youguu.jms.handler.MqHandlerProvider;
import com.youguu.jms.pojo.Message;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.strategy.dao.impl.StrategyUserStockDAO;
import com.youguu.quant.strategy.pojo.StrategyUserStock;
import com.youguu.quant.strategy.service.IStrategyUserStockService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Service("strategyUserStockService")
public class StrategyUserStockService implements IStrategyUserStockService {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_SERVER);

    @Resource
    private StrategyUserStockDAO strategyUserStockDAO;

    @Transactional("quantTradeTx")
    @Override
    public int batchSaveStrategyUserStock(int userId, int strategyId, String stocks) {
        int result = 0;
        List<String> addList = new ArrayList<>();
        List<String> delList = new ArrayList<>();

        //查询数据库中原来的关系
        List<String> oldList = strategyUserStockDAO.findStockCodeList(userId, strategyId);

        if (stocks == null || "".equals(stocks)) {
            delList = oldList;
            result = strategyUserStockDAO.deleteStocks(userId, strategyId, delList);
        } else {
            List<String> newList = new ArrayList(Arrays.asList(stocks.split(",")));

            addList = diffList(oldList, newList);//添加股票列表
            delList = diffList(newList, oldList);//删除股票列表

            List<StrategyUserStock> list = new ArrayList<>();
            StrategyUserStock sut;
            for (String temp : addList) {
                sut = new StrategyUserStock();
                sut.setUserId(userId);
                sut.setStrategyId(strategyId);
                sut.setStockCode(temp);
                sut.setUpdateTime(new Date());
                sut.setCreateTime(new Date());
                list.add(sut);
            }
            result = 1;

            if(delList.size()>0)
            {
                strategyUserStockDAO.deleteStocks(userId, strategyId, delList);
                pushStocks(strategyId, userId, delList, 1);
            }
            if(list.size()>0)
            {
                result = strategyUserStockDAO.batchSaveStrategyUserStock(list);
                pushStocks(strategyId, userId, addList, 2);
            }
        }
        return result;
    }

    /**
     * 计算List差集
     * @param list1
     * @param list2
     * @return
     */
    private List<String> diffList(List list1, List list2){
        List list = new ArrayList(Arrays.asList(new Object[list2.size()]));
        Collections.copy(list, list2);
        list.removeAll(list1);
        return list;
    }

    /**
     *
     * @param strategyId
     * @param userId
     * @param stockList
     * @param type 1-删除；2-新增
     * @return
     */
    private boolean pushStocks(int strategyId, int userId, List<String> stockList, int type){
        if(stockList!=null && stockList.size()>0){
            logger.debug("用户{}修改策略{}下的观察股票{},修改类型type={}【1-删除,2-新增】", userId, strategyId, stockList, type);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", 2);

            JSONArray jsonArray = new JSONArray();
            for(String stockCode : stockList){
                JSONObject obj = new JSONObject();
                obj.put("strategyId", strategyId);
                obj.put("userId", userId);
                obj.put("stockCode", stockCode);
                obj.put("type", type);
                jsonArray.add(obj);
            }
            jsonObject.put("stocks", jsonArray);

            Message<JSONObject> dd = new Message<>(3, jsonObject);
            IMqHandler handler = MqHandlerProvider.get("quant_real_trade_queue");
            return handler.sendMsg(dd);
        }
        return true;
    }

    @Override
    public List<StrategyUserStock> findStockByStrategyAndUser(int userId, int strategyId) {
        return strategyUserStockDAO.findStockByStrategyAndUser(userId, strategyId);
    }

    @Override
    public List<StrategyUserStock> findStockByStrategyAndStock(int strategyId, String stockCode) {
        return strategyUserStockDAO.findStockByStrategyAndStock(strategyId, stockCode);
    }

    @Override
    public List<StrategyUserStock> findAllValidRelation() {
        return strategyUserStockDAO.findAllValidRelation();
    }

    @Override
    public int saveStrategyUserStock(int userId, int strategyId, String stockCode) {
        return strategyUserStockDAO.saveStrategyUserStock(userId, strategyId, stockCode);
    }

    @Override
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode) {
        return strategyUserStockDAO.deleteStrategyUserStock(userId, strategyId, stockCode);
    }

    @Override
    public int selectFollowNum(int userId, int strategyId) {
        return strategyUserStockDAO.selectFollowNum(userId, strategyId);
    }

    @Override
    public StrategyUserStock findStrategyUserStock(int userId, int strategyId, String stockCode) {
        return strategyUserStockDAO.findStrategyUserStock(userId, strategyId, stockCode);
    }

    @Override
    public String findUserFollowStock(int userId, int strategyId) {
        List<String> list =  strategyUserStockDAO.findUserFollowStock(userId, strategyId);
        if(list!=null && list.size()>0){
            StringBuffer sb = new StringBuffer();
            for(String stockCode:list){
                sb.append(",");
                sb.append(stockCode);
            }
            return sb.substring(1);
        }
        return "";
    }
}
