package com.youguu.quant.rpc.server.strategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.youguu.asteroid.rpc.client.AsteroidRPCClientFactory;
import com.youguu.asteroid.rpc.client.tradeday.ITradeDayRPCService;
import com.youguu.cache.config.CacheAnnotation;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.ClassCast;
import com.youguu.core.util.PageHolder;
import com.youguu.core.util.PageHolderSerializer;
import com.youguu.jms.handler.IMqHandler;
import com.youguu.jms.handler.MqHandlerProvider;
import com.youguu.jms.pojo.Message;
import com.youguu.member.client.service.MemberRpcService;
import com.youguu.member.client.service.impl.MemberRpcServiceFactory;
import com.youguu.member.common.pojo.PortfolioTransmit;
import com.youguu.quant.base.QuantTradePlateConfig;
import com.youguu.quant.klinesim.KlineSimResult;
import com.youguu.quant.klinesim.service.KlineSimService;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.rpc.thrift.gen.StrategyServiceThriftRpcService;
import com.youguu.quant.rpc.thrift.gen.StrategyStockOneProfitThrift;
import com.youguu.quant.rpc.thrift.gen.StrategyStockThreeProfitThrift;
import com.youguu.quant.signal.pojo.SignalIndex;
import com.youguu.quant.signal.pojo.StockRealtimeSignal;
import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.signal.service.ISignalService;
import com.youguu.quant.strategy.pojo.*;
import com.youguu.quant.strategy.service.*;
import org.apache.thrift.TException;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import com.youguu.quote.pojo.KLine;
import com.youguu.quote.rpc.client.QuoteClient;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by SomeBody on 2016/8/26.
 */
@Service("strategyServiceThriftRpcService")
public class StrategyServiceThriftRpcServiceImpl implements StrategyServiceThriftRpcService.Iface {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_SERVER);

    @Resource
    private IStrategyService strategyService;
    @Resource
    private IStrategyStockOneProfitService strategyStockOneProfitService;
    @Resource
    private IStrategyStockThreeProfitService strategyStockThreeProfitService;
    @Resource
    private IStrategyUserService strategyUserService;
    @Resource
    private IStrategyUserStockService strategyUserStockService;
    @Resource
    private IStrategyStockBlacklistService strategyStockBlacklistService;
    @Resource
    private IStrategyScoreService strategyScoreService;
    @Resource
    private IStrategyCommentService strategyCommentService;
    @Resource
    private IUserLoopService userLoopService;
    @Resource
    private IRecommendHotStockService recommendHotStockService;
    MemberRpcService asyncMemberRpcService = MemberRpcServiceFactory.getMemberRpcService();
    @Resource
    private IStrategyBestStockService strategyBestStockService;
    @Resource
    private ISignalService signalService;
    @Resource
    private IQuantStkRecordService quantStkRecordService;
    @Resource
    private IStrategyLimitUpService strategyLimitUpService;

    /**
     *  缓存1分钟某个策略最优股票的key
     */
    private final String CASE_STRATEGY_BEST_LIST = "case:strategy:best:list:%s";

    @Resource
    private IStockTradeTodaySerive stockTradeTodaySerive;
    @Resource
    private IStockTradeNear5daysService stockTradeNear5daysService;
    @Resource
    private IStrategyExtendService strategyExtendService;
    @Resource
    private KlineSimService klineSimService;

    ITradeDayRPCService tradeDayRPCService = AsteroidRPCClientFactory.getTradeDayRPCService();

    @Override
    public int saveStrategyBestStock(String stockList) throws TException {
        List<StrategyBestStock> list = JSONArray.parseArray(stockList, StrategyBestStock.class);
        return strategyBestStockService.saveStrategyBestStock(list);
    }

    @Override
    public int updateStrategyBestStock(String stockList) throws TException {
        List<StrategyBestStock> list = JSONArray.parseArray(stockList, StrategyBestStock.class);
        return strategyBestStockService.updateStrategyBestStock(list);
    }

    @Override
    public String queryStrategyBestStock(int num) throws TException {
        List<StrategyBestStock> list = strategyBestStockService.queryStrategyBestStockList(0, num);
        return JSONArray.toJSONString(list);
    }

    @Override
    public String queryAllStrategyBestStock(int strategyId) throws TException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dataSDF = new SimpleDateFormat("yyyyMMddHHmm");
        int limit = 20;
        if(strategyId==0){
            limit = 100;
        }
        List<StrategyBestStock> list = strategyBestStockService.queryStrategyBestStockList(strategyId, limit);
        if (list == null || list.size() == 0) {
            return "";
        }else{
            if(strategyId==0){
                List<StrategyBestStock> tempList = new ArrayList<>();
                List<String> codeList = new ArrayList<>();
                for(StrategyBestStock stock:list){
                    if(tempList.size() == 20){
                        break;
                    }
                    if(codeList.contains(stock.getStockCode())){
                        continue;
                    }
                    codeList.add(stock.getStockCode());
                    tempList.add(stock);
                }
                list = tempList;
            }
        }

        Map<Integer,Strategy> strategy_Map = new HashMap<>();
        for (StrategyBestStock strategyBestStock : list) {
            Strategy strategy = strategy_Map.get(strategyBestStock.getStrategyId());
            if(strategy==null){
                strategy = strategyService.getStrategy(strategyBestStock.getStrategyId());
                if(strategy == null){
                    continue;
                }
                strategy_Map.put(strategyBestStock.getStrategyId(),strategy);
            }
            strategyBestStock.setStrategyName(strategy.getName());
            strategyBestStock.setLogo(strategy.getLogo());
            BigDecimal b = new BigDecimal(strategyBestStock.getProfit()*100);
            strategyBestStock.setProfitStr(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"%");
            try {
                strategyBestStock.setFindTimeStr(sdf.format(dataSDF.parse(strategyBestStock.getFindTime())));
            } catch (ParseException e) {

            }

            b = new BigDecimal(strategyBestStock.getBuyPrice()/10000.00);
           strategyBestStock.setBuyPriceStr(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        }
        StrategyScore strategyScore = strategyScoreService.getStrategyScore(strategyId);
        JSONObject obj = new JSONObject();
        obj.put("list", list);
        if(strategyScore!=null){
            obj.put("num", strategyScore.getSuccessNum());
            BigDecimal b = new BigDecimal(strategyScore.getAverageProfit()*100);
            obj.put("profit", b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"%");
        } else {
            obj.put("num", 0);
            BigDecimal b = new BigDecimal(0);
            obj.put("profit", b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"%");
        }

        return obj.toJSONString();
    }

    @Override
    public String queryAllStrategy(int userId) throws TException {
        //查询全部策略
        List<Strategy> allList = strategyService.findAll();

        if (allList == null || allList.size() == 0) {
            return "";
        }

        //查询用户已购买的未过期策略
        Map<Integer, StrategyUser> myStrategyMap = strategyUserService.findStrategyMapByUserId(userId, 1);
        List<Integer> strategyIdList = new ArrayList<>();
        for (Strategy strategy : allList) {
            strategyIdList.add(strategy.getId());
        }
        //查询策略追踪沪深300近一年收益率
        Map<Integer, StrategyStockOneProfit> profitMap = strategyStockOneProfitService.queryStrategyStockOneProfitMap(strategyIdList);

        for (Strategy s : allList) {
            if (myStrategyMap.containsKey(s.getId())) {
                s.setBuy(true);
            } else {
                s.setBuy(false);
            }
            StrategyStockOneProfit oneProfit = profitMap.get(s.getId());
            if (oneProfit != null) {
                BigDecimal b = new BigDecimal(oneProfit.getProfit()*100);
                s.setHsProfit(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"%");
            }
        }
        return JSONArray.toJSONString(allList);
    }

    @Override
    public int saveStrategyComment(String strategy) throws TException {
        StrategyComment sc = JSONObject.parseObject(strategy, StrategyComment.class);
        return strategyCommentService.saveStrategyComment(sc);
    }

    @Override
    public int deleteStrategyComment(int id) throws TException {
        return strategyCommentService.deleteStrategyComment(id);
    }

    @Override
    public int updateStrategyComment(String strategy) throws TException {
        StrategyComment sc = JSONObject.parseObject(strategy, StrategyComment.class);
        return strategyCommentService.updateStrategyComment(sc);
    }

    @Override
    public String getStrategyComment(int id) throws TException {
        StrategyComment st = strategyCommentService.getStrategyComment(id);
        return JSONObject.toJSONString(st);
    }

    @Override
    public String queryStrategyCommentByPage(int strategyId, int userId, int pageIndex, int pageSize) throws TException {
        Map<String, Object> map = new HashMap<>();
        map.put("strategyId", strategyId);
        map.put("userId", userId);
        PageHolder<StrategyComment> page = strategyCommentService.queryStrategyCommentByPage(map, pageIndex, pageSize);
        String res = toPageJSONString(page);
        return res;
    }

    @Override
    public String findStrategyCommentList(int strategyId, int pageIndex, int pageSize) throws TException {
        List<StrategyComment> list = strategyCommentService.findStrategyCommentList(strategyId, pageIndex, pageSize);
        if (list != null && list.size() > 0) {
            List<Integer> listUid = new ArrayList<>();
            for (StrategyComment sc : list) {
                listUid.add(sc.getUserId());
            }
            JSONObject obj = new JSONObject();
            obj.put("commentList", list);
            String ulist = asyncMemberRpcService.queryUserViewList(listUid);
            JSONArray ja = JSONArray.parseArray(ulist);
            obj.put("userList", ja);
            return obj.toJSONString();
        }
        return null;
    }

    @Override
    public int updateCommentStatus(int id, int status) throws TException {
        return strategyCommentService.updateCommentStatus(id, status);
    }

    @Override
    public int saveStrategyScore(String strategyScore) throws TException {
        StrategyScore ss = JSONObject.parseObject(strategyScore, StrategyScore.class);
        return strategyScoreService.saveStrategyScore(ss);
    }

    @Override
    public String getStrategyScore(int strategyId) throws TException {
        StrategyScore ss = strategyScoreService.getStrategyScore(strategyId);
        return JSONObject.toJSONString(ss);
    }

    @Override
    public int saveStrategy(String strategy) throws TException {
        Strategy s = JSONObject.parseObject(strategy, Strategy.class);
        return strategyService.saveStrategy(s);
    }

    @Override
    public int deleteStrategy(int id) throws TException {
        return strategyService.deleteStrategy(id);
    }

    @Override
    public int updateStrategy(String strategy) throws TException {
        Strategy s = JSONObject.parseObject(strategy, Strategy.class);
        return strategyService.updateStrategy(s);
    }

    @Override
    public String getStrategy(int id) throws TException {
        Strategy s = strategyService.getStrategy(id);
        return JSONObject.toJSONString(s);
    }

    @Override
    public String queryStrategyByPage(int id, int type, int displayStatus, int runStatus, int pageIndex, int pageSize) throws TException {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("id", id);
        map.put("displayStatus", displayStatus);
        map.put("runStatus", runStatus);
        PageHolder<Strategy> pageHolder = strategyService.queryStrategyByPage(map, pageIndex, pageSize);
        SerializeConfig config = new SerializeConfig();
        config.put(PageHolder.class, new PageHolderSerializer());
        return JSON.toJSONString(pageHolder, config);
    }

    private String toPageJSONString(PageHolder page) {
        JSONObject ob = new JSONObject();
        ob.put("pageSize", page.getPageSize());
        ob.put("pageIndex", page.getPageIndex());
        ob.put("totalCount", page.getTotalCount());
        ob.put("code", page.getCode());
        ob.put("list", page.getList());
        return ob.toJSONString();
    }

    @Override
    public int updateStrategyStatus(int id, int status) throws TException {
        return strategyService.updateStrategyStatus(id, status);
    }

    @Override
    public int saveStrategyUser(String strategy) throws TException {
        StrategyUser su = JSONObject.parseObject(strategy, StrategyUser.class);
        return strategyUserService.saveStrategyUser(su);
    }

    @Override
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price) throws TException {
        return strategyUserService.expandExpireTime(userId, strategyId, days, payType, price);
    }

    @Override
    public String getStrategyUser(int id) throws TException {
        StrategyUser su = strategyUserService.getStrategyUser(id);
        return JSONObject.toJSONString(su);
    }

    @Override
    public String findStrategyByUserId(int userId, int expire) throws TException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray = new JSONArray();
        List<StrategyUser> list = strategyUserService.findStrategyByUserId(userId, expire);

        //没有查询到有效期范围内的用户-策略关系
        if(list==null || list.size()==0){
            return JSONArray.toJSONString(jsonArray);
        }

        for (StrategyUser strategyUser : list) {
            JSONObject jsonObject = new JSONObject();
            //查询策略对象
            Strategy strategy = strategyService.getStrategy(strategyUser.getStrategyId());
            if(strategy==null){
                continue;
            }
            //查询用户-策略-观察股票关系列表
            List<StrategyUserStock> strategyUserStockList = strategyUserStockService.findStockByStrategyAndUser(userId, strategyUser.getStrategyId());

            //没有设置股票
            if(strategyUserStockList==null || strategyUserStockList.size()==0){
                jsonObject.put("strategyId", strategy.getId());
                jsonObject.put("name", strategy.getName());
                jsonObject.put("logo", strategy.getLogo());
                jsonObject.put("description", Strategy.FIRST_BUY);
                jsonArray.add(jsonObject);
                continue;
            }

            //提取观察股票的股票代码
            StringBuffer buffer = new StringBuffer();
            for (StrategyUserStock strategyUserStock : strategyUserStockList) {
                buffer.append(strategyUserStock.getStockCode()).append(",");
            }
            //查询观察股票最后一笔交易信号
            Map<String, TradeSignal> lastTradeSignalMap = signalService.getLastTradeSignalMap(strategy.getId(), buffer.toString().split(","));
            //用户购买了策略且添加了观察股票，但是没有交易信号产生
            if(lastTradeSignalMap==null || lastTradeSignalMap.size()==0){
                jsonObject.put("strategyId", strategy.getId());
                jsonObject.put("name", strategy.getName());
                jsonObject.put("logo", strategy.getLogo());
                jsonObject.put("description", Strategy.NO_SIGNAL);
                jsonArray.add(jsonObject);
                continue;
            }

            TradeSignal displayTs = null;//显示信号
            /**
             * 遍历股票列表，查询出所有观察股票中最后发出的信号
             */
            for (StrategyUserStock strategyUserStock : strategyUserStockList) {
                TradeSignal ts = lastTradeSignalMap.get(strategyUserStock.getStockCode());
                if(ts ==null){
                    continue;
                }
                //添加观察股票时间必须小于等于信号产生时间
                if (Long.parseLong(sdf.format(strategyUserStock.getCreateTime())) <= ts.getDatetime()) {
                    //如果当前信号时间大于上一个信号时间，则更新显示信号
                    if(displayTs == null){
                        displayTs = ts;
                    } else if(ts.getDatetime()>displayTs.getDatetime()){
                        displayTs = ts;
                    }
                }
            }
            //设置股票且有信号产生
            if (displayTs!=null) {
                jsonObject.put("strategyId", strategy.getId());
                jsonObject.put("name", strategy.getName());
                jsonObject.put("logo", strategy.getLogo());
                try {
                    BigDecimal b = new BigDecimal(displayTs.getPrice() / 10000d);
                    String price = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
                    //如果小时分钟为0，显示yyyy-MM-dd
                    if(displayTs.getDatetime()%10000==0){
                        jsonObject.put("description", String.format(Strategy.YES_SIGNAL, dateFormat.format(sdf.parse(String.valueOf(displayTs.getDatetime()))), price, "B".equals(displayTs.getDirect())?"模拟买入":"模拟卖出", signalService.getStockName(displayTs.getStockcode())));
                    } else {//如果小时分钟不为0，显示yyyy-MM-dd HH:mm
                        jsonObject.put("description", String.format(Strategy.YES_SIGNAL, minuteFormat.format(sdf.parse(String.valueOf(displayTs.getDatetime()))), price, "B".equals(displayTs.getDirect())?"模拟买入":"模拟卖出", signalService.getStockName(displayTs.getStockcode())));
                    }

                } catch (ParseException e) {
                    //
                }
            } else {
                jsonObject.put("strategyId", strategy.getId());
                jsonObject.put("name", strategy.getName());
                jsonObject.put("logo", strategy.getLogo());
                jsonObject.put("description", Strategy.NO_SIGNAL);
            }

            jsonArray.add(jsonObject);
        }

        return JSONArray.toJSONString(jsonArray);
    }

    @Override
    public String findStrategyUserByUserId(int userId, int expire) throws TException {
        List<StrategyUser> list = strategyUserService.findStrategyByUserId(userId, expire);
        return JSON.toJSONString(list);
    }

    @Override
    public String queryStrategyUserByPage(int userId, int strategyId, int pageIndex, int pageSize) throws TException {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("strategyId", strategyId);
        PageHolder<StrategyUser> page = strategyUserService.queryStrategyUserByPage(map, pageIndex, pageSize);
        String res = toPageJSONString(page);
        return res;
    }

    @Override
    public int batchSaveStrategyUserStock(int userId, int strategyId, String relationList) throws TException {
        return strategyUserStockService.batchSaveStrategyUserStock(userId, strategyId, relationList);
    }

    @Override
    public String findStockByStrategyAndUser(int userId, int strategyId) throws TException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dataSDF = new SimpleDateFormat("yyyyMMddHHmm");
        JSONObject obj = new JSONObject();

        //查询是否过期
        StrategyUser su = strategyUserService.getStrategyUser(userId, strategyId);
        if (su == null) {
            obj.put("status", "0012");
            obj.put("message", "已经超过使用期限，请再次购买");
            return obj.toJSONString();
        }

        List<StrategyUserStock> list = strategyUserStockService.findStockByStrategyAndUser(userId, strategyId);

        //设置用户是否能看到续费按钮
        Strategy s = strategyService.getStrategy(strategyId);
        if (s.getCategoryId() != null && !"".equals(s.getCategoryId())) {
            obj.put("xfFlag", true);
        } else {
            obj.put("xfFlag", false);
        }

        obj.put("expireDate", sdf3.format(su.getExpireTime()));
        obj.put("days", xcts(su.getExpireTime(), new Date()));

        if (list != null && list.size() > 0) {
            Map<String, StockRealtimeSignal> map = signalService.queryStockRealtimeSignalList(strategyId, userId);
            JSONArray ja = new JSONArray();
            JSONObject temp;
            StockRealtimeSignal srs;
            for (StrategyUserStock sus : list) {
                if (sus.getStockCode() != null && !"".equals(sus.getStockCode())) {
                    temp = new JSONObject();
                    temp.put("stockCode", sus.getStockCode());
                    temp.put("stockName", signalService.getStockName(sus.getStockCode()));
                    srs = map.get(sus.getStockCode());
                    if (srs != null) {
                        try {
                            temp.put("date", sdf2.format(dataSDF.parse(srs.getDate())));
                        } catch (ParseException e) {

                        }
                        temp.put("direction", srs.getDirection());
                        temp.put("price", srs.getPrice());
                    } else {
                        logger.debug("stockCode={}, {}", sus.getStockCode(), srs);
                    }
                    ja.add(temp);
                }
            }
            obj.put("list", ja);
        }
        return obj.toJSONString();
    }

    @Override
    public String queryStrategyPortfolioList(int userId, int strategyId, int type) throws TException {
        List<StrategyPortfolio> portfolioList = new ArrayList<>();

        SimpleDateFormat dataSDF = new SimpleDateFormat("yyyyMMddHHmm");
        //策略过期，返回空数组
        StrategyUser su = strategyUserService.getStrategyUser(userId, strategyId);
        if (su == null) {
            return JSON.toJSONString(portfolioList);
        }

        String[] stockCodeArray = queryPortfolio(userId);
        if (null == stockCodeArray || stockCodeArray.length == 0) {
            return JSON.toJSONString(portfolioList);
        }

        Map<String, StockRealtimeSignal> map = signalService.queryStockRealtimeSignalList(strategyId, stockCodeArray);
        for (String stockCode : stockCodeArray) {
            if (stockCode != null && !"".equals(stockCode)) {
                StrategyPortfolio strategyPortfolio = new StrategyPortfolio();
                strategyPortfolio.setStockCode(stockCode);
                strategyPortfolio.setStockName(signalService.getStockName(stockCode));

                StockRealtimeSignal realtimeSignal = map.get(stockCode);
                if (realtimeSignal != null) {
                    try {
                        strategyPortfolio.setTradeTime(dataSDF.parse(realtimeSignal.getDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    strategyPortfolio.setDirection(realtimeSignal.getDirection());
                    strategyPortfolio.setPrice(Double.parseDouble(realtimeSignal.getPrice()));
                } else {
                    logger.debug("stockCode={}, {}", stockCode, realtimeSignal);
                }

                //type等于2，只返回当日有交易的自选股
                if(type == 2){
                    if(null != realtimeSignal){
                        try {
                            Date tradeTime = dataSDF.parse(realtimeSignal.getDate());
                            if(isToday(tradeTime)){
                                portfolioList.add(strategyPortfolio);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    portfolioList.add(strategyPortfolio);//返回全部自选股
                }

            }
        }
        return JSON.toJSONString(portfolioList);
    }

    private boolean isToday(Date tradeTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String param = sdf.format(tradeTime);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

    @Override
    public String forecastPortfolioList(int userId, int strategyId) throws TException {
        List<StrategyPortfolioForecast> portfolioForecastList = new ArrayList<>();

        String[] stockCodeArray = queryPortfolio(userId);
        if (null == stockCodeArray || stockCodeArray.length == 0) {
            return JSON.toJSONString(portfolioForecastList);
        }

        for(String stockCode : stockCodeArray){
            StrategyPortfolioForecast forecast = new StrategyPortfolioForecast();
            KlineSimResult klinesim = klineSimService.getKlineSimById(stockCode);
            forecast.setStockCode(stockCode);
            forecast.setStockName(signalService.getStockName(stockCode));
            if(null != klinesim){
                forecast.setChg(klinesim.getAvgRation());
                forecast.setUpProbability(klinesim.getUpRate());
                forecast.setDownProbability(klinesim.getDownRate());
                forecast.setNodata("2");
            } else {
                forecast.setNodata("1");
            }

            portfolioForecastList.add(forecast);
        }

        return JSON.toJSONString(portfolioForecastList);
    }

    /**
     * 查询用户全部自选股
     * @param userId
     * @return
     */
    private String[] queryPortfolio(int userId){
        PortfolioTransmit pt = asyncMemberRpcService.findPortfolioAStock(userId);
        String[] stockCodeArray = null;
        if(!"".equals(pt.getJsonInfo())){
            JSONArray portfolioArray = JSONArray.parseArray(pt.getJsonInfo());
            JSONObject jsonObject = portfolioArray.getJSONObject(0);
            if(!"".equals(jsonObject.getString("stockList"))){
                stockCodeArray = jsonObject.getString("stockList").split(";");
            }
        }
        return stockCodeArray;
    }

    /**
     * 两个日期相差天数
     */
    private int xcts(Date d1, Date d2) {
        long between_days = (d1.getTime() - d2.getTime()) / (1000 * 3600 * 24);
        int day = Integer.parseInt(String.valueOf(between_days));
        return day <=0 ? 0:day;
    }

    @Override
    public int saveUserLoop(int userId) throws TException {
        return userLoopService.saveUserLoop(userId, UserLoop.TYPE_REPORT);
    }

    @Override
    public int deleteAllUserLoop() throws TException {
        return userLoopService.deleteAllUserLoop();
    }

    @Override
    public int incrLoopNum(int userId) throws TException {
        return userLoopService.incrLoopNum(userId, UserLoop.TYPE_REPORT);
    }

    @Override
    public int incrFinishNum(int userId, int strategyId, String stocks) throws TException {
        return userLoopService.incrBackFinishNum(userId, strategyId, stocks);
    }

    @Override
    public String findUserLoopByUserId(int userId) throws TException {
        UserLoop ul = userLoopService.getUserLoop(userId,UserLoop.TYPE_REPORT);
        return JSONObject.toJSONString(ul);
    }

    @Override
    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfitThrift> thriftList) throws TException {
        List<StrategyStockOneProfit> resultList = new ArrayList<>();
        if (thriftList != null && thriftList.size() > 0) {
            for (StrategyStockOneProfitThrift thrift : thriftList) {
                StrategyStockOneProfit profit = ClassCast.cast(thrift, StrategyStockOneProfit.class);
                resultList.add(profit);
            }
        }
        return strategyStockOneProfitService.batchInsertStrategyStockOneProfit(resultList);
    }

    @Override
    public int truncateStrategyStockOneProfit(int strategyId) throws TException {
        return strategyStockOneProfitService.truncateStrategyStockOneProfit(strategyId);
    }

    @Override
    public String queryStrategyStockOneProfit(int strategyId, String stocks) throws TException {
        List<StrategyStockOneProfit> resultList = strategyStockOneProfitService.queryStrategyStockOneProfit(strategyId, StringUtil.split(stocks, ','));
        return JSON.toJSONString(resultList);
    }

    @Override
    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfitThrift> thriftList) throws TException {
        List<StrategyStockThreeProfit> resultList = new ArrayList<>();
        if (thriftList != null && thriftList.size() > 0) {
            for (StrategyStockThreeProfitThrift thrift : thriftList) {
                StrategyStockThreeProfit profit = ClassCast.cast(thrift, StrategyStockThreeProfit.class);
                resultList.add(profit);
            }
        }
        return strategyStockThreeProfitService.batchInsertStrategyStockThreeProfit(resultList);
    }

    @Override
    public int truncateStrategyStockThreeProfit(int strategyId) throws TException {
        return strategyStockThreeProfitService.truncateStrategyStockThreeProfit(strategyId);
    }

    @Override
    public String queryStrategyStockThreeProfit(int strategyId, String stocks) throws TException {
        List<StrategyStockThreeProfit> resultList = strategyStockThreeProfitService.queryStrategyStockThreeProfit(strategyId, StringUtil.split(stocks, ','));
        return JSON.toJSONString(resultList);
    }

    @Override
    public String findStrategyStockOneProfit(int strategyId, String stockCode) throws TException {
        StrategyStockOneProfit profit = strategyStockOneProfitService.findStrategyStockOneProfit(strategyId, stockCode);
        return JSON.toJSONString(profit);
    }

    @Override
    public String getOneYearAverageProfit(int strategyId, String stocks) throws TException {
        AverageProfit averageProfit = strategyStockOneProfitService.getAverageProfit(strategyId, StringUtil.split(stocks, ','));
        return JSON.toJSONString(averageProfit);
    }

    @Override
    public String getThreeYearAverageProfit(int strategyId, String stocks) throws TException {
        AverageProfit averageProfit = strategyStockThreeProfitService.getAverageProfit(strategyId, StringUtil.split(stocks, ','));
        return JSON.toJSONString(averageProfit);
    }

    public int saveStrategyStockBlacklist(String ssb) throws TException {
        StrategyStockBlacklist s = JSONObject.parseObject(ssb, StrategyStockBlacklist.class);
        return strategyStockBlacklistService.saveStrategyStockBlacklist(s);
    }

    @Override
    public String getStrategyStockBlacklist(int strategyId) throws TException {
        List<StrategyStockBlacklist> list = strategyStockBlacklistService.getStrategyStockBlacklist(strategyId);
        String str = JSONArray.toJSONString(list);
        return str;
    }

    @Override
    public int updateStockBlack(int id, String stockCodes) throws TException {
        return strategyStockBlacklistService.updateStockBlack(id, stockCodes);
    }

    @Override
    public int sendTradeMq(int type, String json) throws TException {
        JSONObject obj = JSONObject.parseObject(json);
        JSONObject res = new JSONObject();
        res.put("strategyid", obj.getString("strategyid"));
        if (type == Strategy.MQ_FLAG_1) {
            res.put("classname", obj.getString("classname"));
        } else if (type == Strategy.MQ_FLAG_4) {
            res.put("starttime", obj.getLongValue("starttime"));
            res.put("endtime", obj.getLongValue("endtime"));
        }
        sendMq(res, "quant_strategy_queue", type);
        return 1;
    }

    @Override
    public String getPermissionNum(int userId, int strategyId) throws TException {
        //查询该用户是否购买了此策略，如果购买的话则可以无限使用
        StrategyUser strategyUser = strategyUserService.getStrategyUser(userId, strategyId);
        JSONObject res = new JSONObject();
        if (strategyUser != null) {
            res.put("buyFlag", true);
            res.put("loopFlag", true);
            res.put("status", "0000");
            res.put("message", "ok");
            return res.toJSONString();
        }

        //判断该用户是否有剩余可回测次数
        UserLoop ul = userLoopService.getUserLoop(userId,UserLoop.TYPE_REPORT);
        if (ul.getLoopNum() > ul.getFinishNum()) {
            res.put("buyFlag", false);
            res.put("loopFlag", true);
            res.put("status", "0000");
            res.put("message", "ok");
            return res.toJSONString();
        }

        //返回该用户可分享次数
        int shareNum = QuantTradePlateConfig.getInstance().getTypeShareNum(UserLoop.TYPE_REPORT) - ul.getShareNum();
        res.put("buyFlag", false);
        res.put("loopFlag", false);
        res.put("shareNum", shareNum);
        res.put("status", "0000");
        res.put("message", "ok");
        return res.toJSONString();

    }

    @Override
    public String queryStrategyReport(int strategyId, String stocks, int userId) throws TException {
        JSONObject obj = new JSONObject();
        int num = stocks.split(",").length;
        if (num < 3 || num > 5) {
            obj.put("status", "0013");
            obj.put("message", "股票数量要大于3支并且小于5支");
            return obj.toJSONString();
        }
        //判断是否有权限
        boolean isflag = strategyUserService.isHavePermission(userId, strategyId);
        if (!isflag) {
            obj.put("status", "0013");
            obj.put("message", "回测次数已用尽");
            return obj.toJSONString();
        }

        try {
            final boolean expire = strategyUserService.isExpire(userId, strategyId);

            //格式为yyyyMMdd
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            int endDate = Integer.parseInt(sdf.format(new Date()));
            if(expire){
                endDate = Integer.valueOf(tradeDayRPCService.nextTradeDay(new Date(), -6));
            } else {
                endDate = Integer.valueOf(tradeDayRPCService.nextTradeDay(new Date(), -1));
            }
            int startDate = endDate - 30000;
            int minDate = endDate - 10000;
            SignalIndex sing = signalService.dataForIndex(strategyId, userId, stocks, startDate, minDate, endDate);

            String str = JSONObject.toJSONString(sing);
            obj = JSONObject.parseObject(str);
            Strategy s = strategyService.getStrategy(strategyId);
            obj.put("name", s.getName());
            obj.put("description", s.getDescription());
            obj.put("logo", s.getLogo());
            obj.put("ratingLabel", s.getRatingLabel());

            obj.put("dataEndDate", endDate);
            obj.put("status", "0000");
            obj.put("message", "ok");

            //消耗一次回测机会
            userLoopService.incrBackFinishNum(userId, strategyId, stocks);

            //查询是否已购买
            StrategyUser isBuy = strategyUserService.getStrategyUser(userId, strategyId);
            if(isBuy != null)
            {
                obj.put("isBuy",true);
            }
            else
            {
                obj.put("isBuy",false);
            }

            return obj.toJSONString();
        } catch (ParseException e) {
            obj.put("status", "0013");
            obj.put("message", "no");
            return obj.toJSONString();
        }
    }

    @Override
    public String findStockByStrategyAndStock(int strategyId, String stockCode) throws TException {
        List<StrategyUserStock> list = strategyUserStockService.findStockByStrategyAndStock(strategyId, stockCode);
        List<Integer> userList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (StrategyUserStock sus : list) {
                userList.add(sus.getUserId());
            }
        }
        List<StrategyUser> strategyUserList = strategyUserService.findByUserIdAndStrategyId(userList, strategyId);
        return JSON.toJSONString(strategyUserList);
    }

    @Override
    public int delRecommendHotStockByType(int type, int strategyId) throws TException {
        return recommendHotStockService.delRecommendHotStockByType(type, strategyId);
    }

    @Override
    public int dredgeStrategyAuthByMq(int userId, int strategyId, int day,String payType,double price) throws TException {
        JSONObject res = new JSONObject();
        res.put("userId", userId);
        res.put("strategyId", strategyId);
        res.put("day", day);
        res.put("payType", payType);
        res.put("price", price);
        sendMq(res, "quant_his_trade_queue", 4);
        return 1;
    }

    @Override
    public String findStrategyByCategoryId(String categoryId) throws TException {
        Strategy res = strategyService.findStrategyByCategoryId(categoryId);
        return JSONObject.toJSONString(res);
    }

    @Override
    public String findAllRunStrategyId() throws TException {
        List<Integer> list = strategyService.findAllRunStrategyId();
        return JSONArray.toJSONString(list);
    }

    @Override
    public String getStrategyUserByUidSid(int userId, int strategyId) throws TException {
        StrategyUser su = strategyUserService.getStrategyUser(userId, strategyId);
        return JSONObject.toJSONString(su);
    }

    @Override
    public int heartBeat() throws TException {
        return 1;
    }

    @Override
    public String disposeQuantStk(int strategyId, String time) throws TException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            return quantStkRecordService.disposeQuantStk(strategyId, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExitsFile(int strategyId) throws TException {
        return strategyService.isExitsFile(strategyId);
    }

    @Override
    public boolean clearStrategyData(int strategyId) throws TException {
        return strategyService.clearStrategyData(strategyId);
    }

    @Override
    public String findAllValidRelation() throws TException {
        List<StrategyUserStock> list = strategyUserStockService.findAllValidRelation();
        return JSON.toJSONString(list);
    }

    @Override
    public String findStrategyBuyUserCount(String time) throws TException {
        List<StrategyUser> list = strategyUserService.findStrategyBuyUserCount(time);
        if(list != null && list.size()>0)
        {
            JSONArray ja = new JSONArray();
            JSONObject res = new JSONObject();
            JSONObject obj;
            int count = 0;

            Map<Integer,String> strategyMap = new HashMap<>();
            //关联策略ID跟策略名称
            List<Strategy> allList =  strategyService.findAll();
            for(Strategy s1:allList)
            {
                strategyMap.put(s1.getId(),s1.getName());
            }

            for(StrategyUser su:list)
            {
                obj = new JSONObject();
                obj.put("name",strategyMap.get(su.getStrategyId()));
                obj.put("num",su.getNum());
                ja.add(obj);
            }
            res.put("list",ja);
            res.put("count",strategyUserService.findStrategyBuyUserAllCount(time));
            return res.toJSONString();
        }
        return null;
    }

    @Override
    public String findStrategyUserLoopCount(String time) throws TException {
        //查询用户跟策略的关系
        List<StrategyUser> buyList = strategyUserService.findStrategyBuyUserList(time);

        //存储用户id+策略ID的关系
        Set<String> set = new HashSet<>();
        if(buyList != null && buyList.size()>0)
        {
            for(StrategyUser su:buyList)
            {

                set.add(su.getUserId()+";"+su.getStrategyId());
            }
        }

        //关联策略ID跟策略名称
        Map<Integer,String> strategyMap = new HashMap<>();
        List<Strategy> allList =  strategyService.findAll();
        for(Strategy s1:allList)
        {
            strategyMap.put(s1.getId(),s1.getName());
        }

        //查询用户回测记录
        List<UserLoopRecord> loopList = userLoopService.findUserLoopList(time);
        if(loopList != null && loopList.size() > 0)
        {
            String key = "";
            int sid = 0;
            int num = 0;

            //已购买用户当日回测接口调用总次数
            Map<Integer,Integer> okCountMap = new HashMap<>();

            //未购买用户当日回测接口调用总次数
            Map<Integer,Integer> noCountMap = new HashMap<>();

            //已购买用户当日回测接口调用总人数
            Map<Integer,Integer> okUserMap = new HashMap<>();

            //未购买用户当日回测接口调用总人数
            Map<Integer,Integer> noUserMap = new HashMap<>();

            for(UserLoopRecord record:loopList)
            {
                key = record.getUserId()+";"+record.getStrategyId();
                sid = record.getStrategyId();
                num = record.getNum();
                //如果已购买
                if(set.contains(key))
                {
                    //处理总人数
                    if(okUserMap.containsKey(sid))
                    {
                        okUserMap.put(sid,okUserMap.get(sid)+1);
                    }
                    else
                    {
                        okUserMap.put(sid,1);
                    }

                    //处理总次数
                    if(okCountMap.containsKey(sid))
                    {
                        okCountMap.put(sid,okCountMap.get(sid)+num);
                    }
                    else
                    {
                        okCountMap.put(sid,num);
                    }
                }//如果未购买
                else
                {
                    //处理总人数
                    if(noUserMap.containsKey(sid))
                    {
                        noUserMap.put(sid,noUserMap.get(sid)+1);
                    }
                    else
                    {
                        noUserMap.put(sid,1);
                    }

                    //处理总次数
                    if(noCountMap.containsKey(sid))
                    {
                        noCountMap.put(sid,noCountMap.get(sid)+num);
                    }
                    else
                    {
                        noCountMap.put(sid,num);
                    }
                }
            }

            //拼装返回数据

            JSONArray jsonList = new JSONArray();//未购买用户当日回测接口调用总次数
            JSONObject loopObj;
            int allUser = 0;//当日回测总人数
            int allCount = 0;//当日回测总次数

            for(Strategy su:allList)
            {
                loopObj = new JSONObject();

                int okBuyUser = okUserMap.get(su.getId()) == null ? 0:okUserMap.get(su.getId());//已购买用户回测总人数
                int noBuyUser = noUserMap.get(su.getId()) == null ? 0:noUserMap.get(su.getId());//未购买用户回测总人数
                int okBuyCount = okCountMap.get(su.getId()) == null ? 0:okCountMap.get(su.getId());//已购买用户回测总次数
                int noBuyCount = noCountMap.get(su.getId()) == null ? 0:noCountMap.get(su.getId());//未购买用户回测总次数
                int allBuyUser = okBuyUser + noBuyUser;//单策略总人数
                int allBuyCount = okBuyCount + noBuyCount;//单策略总次数
                allUser += allBuyUser;
                allCount += allBuyCount;

                loopObj.put("name",strategyMap.get(su.getId()));//策略名称
                loopObj.put("okBuyUser",okBuyUser);
                loopObj.put("noBuyUser",noBuyUser);
                loopObj.put("okBuyCount",okBuyCount);
                loopObj.put("noBuyCount",noBuyCount);
                loopObj.put("allBuyUser",allBuyUser);
                loopObj.put("allBuyCount",allBuyCount);

                jsonList.add(loopObj);
            }

            JSONObject res = new JSONObject();
            res.put("list",jsonList);
            res.put("allUser",allUser);
            res.put("allCount",allCount);

            return res.toJSONString();
        }
        return null;
    }

    @CacheAnnotation(poolName = "user", field = CacheAnnotation.FIELD_AUTO ,expire = 60)
    @Override
    public String queryStrategyBestStockListBySid(int sid, int limit) throws TException {
        //优先从缓存取，缓存1分钟
//        RedisPool pool = RedisUtil.getRedisPool("capture");
//        String caseList = pool.get(String.format(CASE_STRATEGY_BEST_LIST,sid));
//        if(caseList != null && !"".equals(caseList))
//        {
//            return caseList;
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dataSDF = new SimpleDateFormat("yyyyMMddHHmm");

        List<StrategyBestStock> list = strategyBestStockService.queryStrategyBestStockListBySid(sid, limit);
        if (list == null || list.size() == 0) {
            return null;
        }

        for (StrategyBestStock strategyBestStock : list) {
            BigDecimal b = new BigDecimal(strategyBestStock.getProfit() * 100);
            strategyBestStock.setProfitStr(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "%");
            try {
                strategyBestStock.setFindTimeStr(sdf.format(dataSDF.parse(strategyBestStock.getFindTime())));
            } catch (ParseException e) {
            }
        }

        String resultStr = JSONArray.toJSONString(list);
//        pool.set(String.format(CASE_STRATEGY_BEST_LIST,sid),resultStr);
//        pool.expire(String.format(CASE_STRATEGY_BEST_LIST,sid),60);
        return resultStr;
    }

    @Override
    public int saveStrategyUserStock(int userId, int strategyId, String stockCode) throws TException {
        return strategyUserStockService.saveStrategyUserStock(userId, strategyId, stockCode);
    }

    @Override
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode) throws TException {
        return strategyUserStockService.deleteStrategyUserStock(userId, strategyId, stockCode);
    }

    @Override
    public int selectFollowNum(int userId, int strategyId) throws TException {
        return strategyUserStockService.selectFollowNum(userId, strategyId);
    }

    @Override
    public String queryTodayTrade(int strategyId, int type, long seq, int num) throws TException {
        List<StockTradeToday> list =  stockTradeTodaySerive.queryTodayTrade(strategyId, type, seq, num);
        if(list!=null){
            return JSONArray.toJSONString(list);
        }else{
            return new JSONArray().toJSONString();
        }

    }

    @Override
    public int addTodayTrade(int strategyId, String stockTradeToday) throws TException {
        StockTradeToday stt = JSON.parseObject(stockTradeToday,StockTradeToday.class);
        int result = stockTradeTodaySerive.saveStockTradeToday(stt);
        return result;
    }

    @Override
    public int clearTodayTrade(int strategyId, int positionNum) throws TException {

        return stockTradeTodaySerive.clearData(strategyId,positionNum);
    }

    @Override
    public int updateTodayBuy(int strategyId, String stockTradeTodayList) throws TException {
        List<StockTradeToday> list = JSONArray.parseArray(stockTradeTodayList, StockTradeToday.class);
        return stockTradeTodaySerive.updateStockTradeTodayProfit(list);
    }

    @Override
    public int addStockTradeNear5Days(int strategyId, String stockTradeNear5DaysList) throws TException {
        List<StockTradeNear5days> list = JSONArray.parseArray(stockTradeNear5DaysList, StockTradeNear5days.class);
        return stockTradeNear5daysService.saveList(list);
    }

    @Override
    public int clearNear5DaysTrade(int strategyId) throws TException {
        return stockTradeNear5daysService.clearData(strategyId);
    }

    @Override
    public String queryDays5Trade(int strategyId, long seq, int num) throws TException {
        List<StockTradeNear5days> list = stockTradeNear5daysService.queryData(strategyId, seq, num);
        if(list!=null){
            return JSONArray.toJSONString(list);
        }else{
            return new JSONArray().toJSONString();
        }

    }

    @Override
    public String searchStockResult(int userId, int strategyId, String stockCode) throws TException {
        TradeSignal ts = signalService.getLastTradeSignal(strategyId, stockCode);
        StrategyUserStock sus = strategyUserStockService.findStrategyUserStock(userId, strategyId, stockCode);
        StockSearchResponse response = new StockSearchResponse();
        response.setStockCode(stockCode);
        response.setStockName(signalService.getStockName(stockCode));

        if(sus!=null){
            response.setFollow(true);
        } else {
            response.setPosition(false);
        }

        if(ts!=null && ("H".equals(ts.getDirect()) || "B".equals(ts.getDirect()))){
            response.setPosition(true);
        } else {
            response.setPosition(false);
        }

        return JSON.toJSONString(response);
    }

    @Override
    public String getStrategyExtend(int strategyId) throws TException {
        return JSON.toJSONString(strategyExtendService.getStrategyExtend(strategyId));
    }

    @Override
    public String findUserFollowStock(int userId, int strategyId) throws TException {
        return strategyUserStockService.findUserFollowStock(userId, strategyId);
    }

    @Override
    public String findReportData(int day,double lt,double rt,int num) throws TException {
        JSONObject res = new JSONObject();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //查询涨停最多的机器人的信息
        StrategyLimitUp slu = strategyLimitUpService.getMax(sdf.format(c.getTime()),sdf.format(new Date()));
        if(slu == null)
        {
            return "";
        }
        Strategy s = strategyService.getStrategy(slu.getStrategyId());
        res.put("name",s.getName());
        res.put("num",slu.getNum());
        res.put("pic",s.getLogo());
        res.put("sid",s.getId());

        //查询这个策略下近五日涨幅的股票
        List<StockTradeNear5days> list = stockTradeNear5daysService.queryData(slu.getStrategyId(),0,1000);
        JSONObject obj;
        //过滤出符合条件的股票
        List<StockTradeNear5days> zyList = new ArrayList<>();
        if(list != null && list.size() >= 0)
        {
            for(StockTradeNear5days dayFive:list)
            {
                if(dayFive.getProfit() >=lt && dayFive.getProfit() <= rt)
                {
                    zyList.add(dayFive);
                }
            }
        }

        //取行情30日，计算出每只股票的30日上涨次数
        List<KLine> kList;
        int dayNum = 0;
        Zygp zygp;
        List<Zygp> zygpList = new ArrayList<>();
        for(StockTradeNear5days zy:zyList)
        {
            kList = QuoteClient.getNDaysKLineList(zy.getStockCode(),sdf.format(new Date()),1,30,-1);
            if(kList != null && kList.size()>0)
            {
                for(KLine lin: kList)
                {
                    if(lin.getCurPx() > lin.getLastClosePx())
                    {
                        dayNum++;
                    }
                }
                zygp = new Zygp();
                zygp.setCode(zy.getStockCode());
                zygp.setName(zy.getStockName());
                zygp.setDay(dayNum);
                zygpList.add(zygp);
                dayNum = 0;
            }
        }

        //排序
        Collections.sort(zygpList);
        JSONArray ja = new JSONArray();
        for(int i=0;i<num;i++)
        {
            ja.add(zygpList.get(i));
        }
        res.put("zy",ja);
        return res.toJSONString();
    }

    @Override
    public String findAllStrategyUser(String time) throws TException {
        List<StrategyUser> list = strategyUserService.findStrategyBuyUserList(time);
        if(list == null || list.size() == 0)
        {
            return "";
        }
        List<Integer> uids = new ArrayList<>();
        for(StrategyUser su:list)
        {
            uids.add(su.getUserId());
        }
        return JSONArray.toJSONString(uids);
    }

    /**
     *  最优股票
     */
    class Zygp  implements Comparable<Zygp>
    {
        private String code;
        private String name;
        private int day;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public int compareTo(Zygp o) {
            return o.getDay()-this.day;
        }
    }

    @Override
    public String searchStrategyBestStock() throws TException {
        return JSON.toJSONString(strategyBestStockService.searchStrategyBestStock());
    }

    public int addRecommendHotStock(String recommendHotStock) throws TException {
        RecommendHotStock rhs = JSON.parseObject(recommendHotStock, RecommendHotStock.class);
        return recommendHotStockService.addRecommendHotStock(rhs);
    }

    @Override
    public int batchAddRecommendHotStock(String recommendHotStockList) throws TException {
        List<RecommendHotStock> rhs = JSON.parseArray(recommendHotStockList, RecommendHotStock.class);
        return recommendHotStockService.batchAddRecommendHotStock(rhs);
    }

    @Override
    public int delRecommendHotStock(int strategyId) throws TException {
        return recommendHotStockService.delRecommendHotStock(strategyId);
    }

    @Override
    public String getRecommendHotStockList(int strategyId, int type) throws TException {
        List<RecommendHotStock> list = recommendHotStockService.getRecommendHotStockList(strategyId, type);
        if (list != null && list.size() > 0) {
            for (RecommendHotStock shs : list) {
                shs.setStockName(signalService.getStockName(shs.getStockCode()));
            }
        }
        return JSON.toJSONString(list);
    }

    private void sendMq(JSONObject json, String handler, int type) {
        Message dd = new Message(type, json);
        IMqHandler mq = MqHandlerProvider.get(handler);
        mq.sendMsg(dd);
    }

    @Override
    public int incrDNALoopNum(int userId) throws TException {
        return userLoopService.incrLoopNum(userId,UserLoop.TYPE_DNA);
    }

    @Override
    public String findDNALoopByUserId(int userId) throws TException {
        DNAAcl acl = userLoopService.queryDNANum(userId);
        return JSON.toJSONString(acl);
    }
}
