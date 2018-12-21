package com.youguu.quant.rpc.client.strategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.ClassCast;
import com.youguu.core.util.PageHolder;
import com.youguu.core.util.PageHolderDeserializer;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.rpc.thrift.gen.StrategyStockOneProfitThrift;
import com.youguu.quant.rpc.thrift.gen.StrategyStockThreeProfitThrift;
import com.youguu.quant.strategy.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SomeBody on 2016/8/26.
 */
@Service("strategyRpcService")
public class StrategyRpcServiceImpl implements IStrategyRpcService {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_CLIENT);

    private StrategyClient getClient() {
        return new StrategyClient();
    }

    @Override
    public int saveStrategyBestStock(List<StrategyBestStock> list) {
        try {
            String arg = JSONArray.toJSONString(list);
            return getClient().saveStrategyBestStock(arg);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int updateStrategyBestStock(List<StrategyBestStock> list) {
        try {
            String arg = JSONArray.toJSONString(list);
            return getClient().updateStrategyBestStock(arg);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public String queryAllStrategyBestStock(int strategyId) {
        try {
            return getClient().queryAllStrategyBestStock(strategyId);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStock(int num) {
        try {
            String str = getClient().queryStrategyBestStock(num);
            List<StrategyBestStock> list = JSONArray.parseArray(str,StrategyBestStock.class);
            return list;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Strategy> queryAllStrategy(int userId) {
        try {
            String str = getClient().queryAllStrategy(userId);
            List<Strategy> list = JSONArray.parseArray(str,Strategy.class);
            return list;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int saveStrategyComment(StrategyComment strategy) {
        try {
            String str = JSONObject.toJSONString(strategy);
            return getClient().saveStrategyComment(str);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int deleteStrategyComment(int id) {
        try {
            return getClient().deleteStrategyComment(id);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int updateStrategyComment(StrategyComment strategy) {
        try {
            String str = JSONObject.toJSONString(strategy);
            return getClient().updateStrategyComment(str);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public StrategyComment getStrategyComment(int id) {
        try {
            String str = getClient().getStrategyComment(id);
            return JSONObject.parseObject(str, StrategyComment.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public PageHolder<StrategyComment> queryStrategyCommentByPage(int strategyId,int userId, int pageIndex, int pageSize) {
        try {
            String str = getClient().queryStrategyCommentByPage(strategyId, userId, pageIndex, pageSize);
            PageHolder<StrategyComment> page = getPageDate(str, StrategyComment.class);
            return page;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String findStrategyCommentList(int strategyId, int pageIndex, int pageSize) {
        try {
            return getClient().findStrategyCommentList(strategyId, pageIndex, pageSize);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int updateCommentStatus(int id, int status) {
        try {
            return getClient().updateCommentStatus(id, status);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int saveStrategyScore(StrategyScore strategyScore) {
        try {
            String res = JSONObject.toJSONString(strategyScore);
            return getClient().saveStrategyScore(res);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public StrategyScore getStrategyScore(int strategyId) {
        try {
            String res = getClient().getStrategyScore(strategyId);
            StrategyScore ss = JSONObject.parseObject(res, StrategyScore.class);
            return ss;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int saveStrategy(Strategy strategy) {
        try {
            String res = JSONObject.toJSONString(strategy);
            return getClient().saveStrategy(res);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int deleteStrategy(int id) {
        try {
            return getClient().deleteStrategy(id);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int updateStrategy(Strategy strategy) {
        try {
            String res = JSONObject.toJSONString(strategy);
            return getClient().updateStrategy(res);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public Strategy getStrategy(int id) {
        try {
            String res = getClient().getStrategy(id);
            Strategy s = JSONObject.parseObject(res, Strategy.class);
            return s;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public PageHolder<Strategy> queryStrategyByPage(int id,int type,int displayStatus,int runStatus,int pageIndex,int pageSize) {
        try {
            String json = getClient().queryStrategyByPage(id, type, displayStatus, runStatus, pageIndex, pageSize);
            ParserConfig.getGlobalInstance().putDeserializer(PageHolder.class, new PageHolderDeserializer());
            return JSON.parseObject(json, new TypeReference<PageHolder<Strategy>>(){});
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    private PageHolder getPageDate(String json,Class clz)
    {
        JSONObject ob = JSONObject.parseObject(json);
        PageHolder newPage = new PageHolder();
        newPage.setCode(ob.getString("code"));
        newPage.setPageIndex(ob.getIntValue("pageIndex"));
        newPage.setPageSize(ob.getIntValue("pageSize"));
        newPage.setTotalCount(ob.getIntValue("totalCount"));
        String list = ob.getString("list");
        if(list != null && !"".equals(list) && !"[]".equals(list))
        {
            List listData = JSONArray.parseArray(list,clz);
            newPage.addAll(listData);
        }
        return newPage;
    }

    @Override
    public int updateStrategyStatus(int id, int status) {
        try {
            return getClient().updateStrategyStatus(id, status);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int saveStrategyUser(StrategyUser strategy) {
        try {
            String res = JSONObject.toJSONString(strategy);
            return getClient().saveStrategyUser(res);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price) {
        try {
            return getClient().expandExpireTime(userId, strategyId, days,payType,price);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public StrategyUser getStrategyUser(int id) {
        try {
            String res = getClient().getStrategy(id);
            StrategyUser su = JSONObject.parseObject(res,StrategyUser.class);
            return su;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String findStrategyByUserId(int userId, int expire) {
        try {
            return getClient().findStrategyByUserId(userId, expire);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StrategyUser> findStrategyUserByUserId(int userId, int expire) {
        try {
            String json = getClient().findStrategyUserByUserId(userId, expire);
            if(!"".equals(json)){
                List<StrategyUser> list = JSON.parseArray(json, StrategyUser.class);
                return list;
            }
            return null;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public PageHolder<StrategyUser> queryStrategyUserByPage(int userId,int strategyId, int pageIndex, int pageSize) {
        try {
            String str = getClient().queryStrategyUserByPage(userId, strategyId, pageIndex, pageSize);
            PageHolder<StrategyUser> page = getPageDate(str,StrategyUser.class);
            return page;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int batchSaveStrategyUserStock(int userId, int strategyId,String relationList) {
        try {
            return getClient().batchSaveStrategyUserStock(userId, strategyId,relationList);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public String findStockByStrategyAndUser(int userId, int strategyId) {
        try {
            return getClient().findStockByStrategyAndUser(userId,strategyId);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int saveUserLoop(int userId) {
        try {
            return getClient().saveUserLoop(userId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int incrLoopNum(int userId) {
        try {
            return getClient().incrLoopNum(userId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int incrFinishNum(int userId, int strategyId, String stocks) {
        try {
            return getClient().incrFinishNum(userId, strategyId,stocks);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public UserLoop findUserLoopByUserId(int userId) {
        try {
            String res = getClient().findUserLoopByUserId(userId);
            return JSONObject.parseObject(res,UserLoop.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list) {
        List<StrategyStockOneProfitThrift> thriftList = new ArrayList<>();
        if(list!=null && list.size()>0){
            for(StrategyStockOneProfit profit : list){
                thriftList.add(ClassCast.cast(profit, StrategyStockOneProfitThrift.class));
            }
        }
        try {
            return getClient().batchInsertStrategyStockOneProfit(thriftList);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }

    }

    @Override
    public int truncateStrategyStockOneProfit(int strategyId) {
        try {
            return getClient().truncateStrategyStockOneProfit(strategyId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks) {
        try {
            String json = getClient().queryStrategyStockOneProfit(strategyId, StringUtils.join(stocks, ','));
            return JSON.parseArray(json, StrategyStockOneProfit.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfit> list) {
        List<StrategyStockThreeProfitThrift> thriftList = new ArrayList<>();
        if(list!=null && list.size()>0){
            for(StrategyStockThreeProfit profit : list){
                thriftList.add(ClassCast.cast(profit, StrategyStockThreeProfitThrift.class));
            }
        }
        try {
            return getClient().batchInsertStrategyStockThreeProfit(thriftList);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int truncateStrategyStockThreeProfit(int strategyId) {
        try {
            return getClient().truncateStrategyStockThreeProfit(strategyId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public List<StrategyStockThreeProfit> queryStrategyStockThreeProfit(int strategyId, String... stocks) {
        try {
            String json = getClient().queryStrategyStockThreeProfit(strategyId, StringUtils.join(stocks, ','));
            return JSON.parseArray(json, StrategyStockThreeProfit.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode) {
        try {
            String json = getClient().findStrategyStockOneProfit(strategyId, stockCode);
            return JSON.parseObject(json, StrategyStockOneProfit.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }
    public int saveStrategyStockBlacklist(StrategyStockBlacklist ssb) {
        try {
            String str = JSONObject.toJSONString(ssb);
            return getClient().saveStrategyStockBlacklist(str);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public AverageProfit getOneYearAverageProfit(int strategyId, String... stocks){
        try {
            String json = getClient().getOneYearAverageProfit(strategyId, StringUtils.join(stocks, ','));
            return JSON.parseObject(json, AverageProfit.class);
        }catch (TException e) {
            logger.error(e);
            return null;
        }
    }
    public List<StrategyStockBlacklist> getStrategyStockBlacklist(int strategyId) {
        try {
            String json = getClient().getStrategyStockBlacklist(strategyId);
            return JSONArray.parseArray(json,StrategyStockBlacklist.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int updateStockBlack(int id, String stockCodes) {
        try {
            return getClient().updateStockBlack(id, stockCodes);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int sendTradeMq(int type, String json) {
        try {
            return getClient().sendTradeMq(type, json);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public String getPermissionNum(int userId,int strategyId) {
        try {
            return getClient().getPermissionNum(userId, strategyId);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String queryStrategyReport(int strategyId, String stocks, int userId) {
        try {
            return getClient().queryStrategyReport(strategyId, stocks, userId);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int deleteAllUserLoop() {
        try {
            return getClient().deleteAllUserLoop();
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public List<StrategyUser> findStockByStrategyAndStock(int strategyId, String stockCode) {
        try {
            String json =  getClient().findStockByStrategyAndStock(strategyId,stockCode);
            if(!"".equals(json)){
                return JSON.parseObject(json, new TypeReference<List<StrategyUser>>(){});
            }
        } catch (TException e) {
            logger.error(e);
            return null;
        }
        return null;
    }

    @Override
    public int dredgeStrategyAuthByMq(int userId, int strategyId, int day,String payType,double price) {
        try {
            return getClient().dredgeStrategyAuthByMq(userId, strategyId, day,payType,price);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public Strategy findStrategyByCategoryId(String categoryId) {
        try {
            String str = getClient().findStrategyByCategoryId(categoryId);
            Strategy s = JSONObject.parseObject(str,Strategy.class);
            return s;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Integer> findAllRunStrategyId() {
        try {
            String listStr = getClient().findAllRunStrategyId();
            List<Integer> list = JSONArray.parseArray(listStr, Integer.class);
            return list;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public StrategyUser getStrategyUserByUidSid(int userId, int strategyId) {
        try {
            String str = getClient().getStrategyUserByUidSid(userId, strategyId);
            StrategyUser su = JSONObject.parseObject(str, StrategyUser.class);
            return su;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int heartBeat() {
        try {
            return getClient().heartBeat();
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public String disposeQuantStk(int strategyId, Date time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return getClient().disposeQuantStk(strategyId, sdf.format(time));
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean isExitsFile(int strategyId) {
        try {
            return getClient().isExitsFile(strategyId);
        } catch (TException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean clearStrategyData(int strategyId) {
        try {
            return getClient().clearStrategyData(strategyId);
        } catch (TException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public List<StrategyUserStock> findAllValidRelation() {
        try {
            String str = getClient().findAllValidRelation();
            List<StrategyUserStock> list = JSONArray.parseArray(str,StrategyUserStock.class);
            return list;
        } catch (TException e) {
            logger.error(e);
            return null;
        }

    }

    @Override
    public String findStrategyBuyUserCount(String time) {
        try {
            String str = getClient().findStrategyBuyUserCount(time);
            return str;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String findStrategyUserLoopCount(String time) {
        try {
            String str = getClient().findStrategyUserLoopCount(time);
            return str;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid, int limit) {
        try {
            String str = getClient().queryStrategyBestStockListBySid(sid, limit);
            if(str != null && !"".equals(str))
            {
                List<StrategyBestStock> list = JSONArray.parseArray(str,StrategyBestStock.class);
                return list;
            }
            return null;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    public int saveStrategyUserStock(int userId, int strategyId, String stockCode) {
        try {
            return getClient().saveStrategyUserStock(userId, strategyId, stockCode);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode) {
        try {
            return getClient().deleteStrategyUserStock(userId, strategyId, stockCode);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int selectFollowNum(int userId, int strategyId) {
        try {
            return getClient().selectFollowNum(userId, strategyId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public StockSearchResponse searchStockResult(int userId, int strategyId, String stockCode) {
        try {
            String json = getClient().searchStockResult(userId, strategyId, stockCode);
            return JSON.parseObject(json, StockSearchResponse.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int addRecommendHotStock(RecommendHotStock recommendHotStock) {
        try {
            return getClient().addRecommendHotStock(JSON.toJSONString(recommendHotStock));
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int batchAddRecommendHotStock(List<RecommendHotStock> list) {
        try {
            return getClient().batchAddRecommendHotStock(JSON.toJSONString(list));
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public int delRecommendHotStock(int strategyId) {
        try {
            return getClient().delRecommendHotStock(strategyId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public List<RecommendHotStock> getRecommendHotStockList(int strategyId, int type) {
        try {
            String json = getClient().getRecommendHotStockList(strategyId, type);
            return JSONArray.parseArray(json, RecommendHotStock.class);
        }catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int delRecommendHotStockByType(int type, int strategyId) {
        try {
            return getClient().delRecommendHotStockByType(type, strategyId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public AverageProfit getThreeYearAverageProfit(int strategyId, String... stocks) {
        try {
            String json = getClient().getThreeYearAverageProfit(strategyId, StringUtils.join(stocks, ','));
            return JSON.parseObject(json, AverageProfit.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StockTradeToday> queryTodayTrade(int strategyId, int type, long seq, int num) {
        try {
            String json = getClient().queryTodayTrade(strategyId, type, seq, num);

            return JSONArray.parseArray(json, StockTradeToday.class);
        } catch (TException e) {
            logger.error("queryTodayTrade error",e);
            return null;
        }
    }

    @Override
    public int addTodayTrade(int strategyId, StockTradeToday stockTradeToday) {
        try {
            int result = getClient().addTodayTrade(strategyId, JSON.toJSONString(stockTradeToday));

            return result;

        } catch (TException e) {
            logger.error("addTodayTrade error",e);
            return 0;
        }
    }

    @Override
    public int clearTodayTrade(int strategyId, int positionNum) {
        try {
            int result = getClient().clearTodayTrade(strategyId, positionNum);

            return result;

        } catch (TException e) {
            logger.error("clearTodayTrade error",e);
            return 0;
        }
    }

    @Override
    public int updateTodayBuy(int strategyId, List<StockTradeToday> stockTradeTodayList) {
        try {
            if(stockTradeTodayList!=null && stockTradeTodayList.size()>0){
                String json = JSONArray.toJSONString(stockTradeTodayList);
                int result = getClient().updateTodayBuy(strategyId, json);
                return result;
            }else{
                return 0;
            }
        } catch (TException e) {
            logger.error("clearTodayTrade error",e);
            return 0;
        }
    }

    @Override
    public int addStockTradeNear5Days(int strategyId, List<StockTradeNear5days> stockTradeNear5DaysList) {
        try {
            if(stockTradeNear5DaysList!=null && stockTradeNear5DaysList.size()>0){
                String json = JSONArray.toJSONString(stockTradeNear5DaysList);
                int result = getClient().addStockTradeNear5Days(strategyId, json);
                return result;
            }else{
                return 0;
            }
        } catch (TException e) {
            logger.error("clearTodayTrade error",e);
            return 0;
        }
    }

    @Override
    public int clearNear5DaysTrade(int strategyId) {
        try {
            int result = getClient().clearNear5DaysTrade(strategyId);

            return result;

        } catch (TException e) {
            logger.error("clearTodayTrade error",e);
            return 0;
        }
    }

    @Override
    public List<StockTradeNear5days> queryDays5Trade(int strategyId, long seq, int num) {
        try {
            String json = getClient().queryDays5Trade(strategyId, seq, num);

            return JSONArray.parseArray(json, StockTradeNear5days.class);

        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public StrategyExtend getStrategyExtend(int strategyId) {
        try {
            String json = getClient().getStrategyExtend(strategyId);
            return JSON.parseObject(json, StrategyExtend.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String findUserFollowStock(int userId, int strategyId) {
        try {
            return getClient().findUserFollowStock(userId, strategyId);

        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String findReportData(int day, double lt, double rt, int num) {
        try {
            return getClient().findReportData(day, lt, rt, num);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Integer> findAllStrategyUser(String time) {
        try {
            String uids = getClient().findAllStrategyUser(time);
            if(!"".equals(uids))
            {
                List<Integer> list = JSONArray.parseArray(uids,Integer.class);
                return list;
            }
            return null;
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public StrategySearch searchStrategyBestStock() {
        try {
            String s = getClient().searchStrategyBestStock();
            return JSON.parseObject(s, StrategySearch.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public int incrDNALoopNum(int userId) {
        try {
            return  getClient().incrDNALoopNum(userId);
        } catch (TException e) {
            logger.error(e);
            return 0;
        }
    }

    @Override
    public DNAAcl findDNALoopByUserId(int userId) {
        try {
            String res = getClient().findDNALoopByUserId(userId);
            return JSON.parseObject(res, DNAAcl.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StrategyPortfolio> queryStrategyPortfolioList(int userId, int strategyId, int type) {
        try {
            String json =  getClient().queryStrategyPortfolioList(userId, strategyId, type);
            return JSONArray.parseArray(json, StrategyPortfolio.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<StrategyPortfolioForecast> forecastPortfolioList(int userId, int strategyId) {
        try {
            String json =  getClient().forecastPortfolioList(userId, strategyId);
            return JSONArray.parseArray(json, StrategyPortfolioForecast.class);
        } catch (TException e) {
            logger.error(e);
            return null;
        }
    }
}
