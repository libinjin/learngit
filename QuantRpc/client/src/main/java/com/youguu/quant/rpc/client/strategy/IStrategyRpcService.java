package com.youguu.quant.rpc.client.strategy;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.pojo.*;

import java.util.Date;
import java.util.List;

/**
 * Created by SomeBody on 2016/8/26.
 */
public interface IStrategyRpcService {
    public int saveStrategyBestStock(List<StrategyBestStock> list);

    public int updateStrategyBestStock(List<StrategyBestStock> list);

    public String queryAllStrategyBestStock(int strategyId);

    public List<StrategyBestStock> queryStrategyBestStock(int num);

    public List<Strategy> queryAllStrategy(int userId);

    public int saveStrategyComment(StrategyComment strategy);

    public int deleteStrategyComment(int id);

    public int updateStrategyComment(StrategyComment strategy);

    public StrategyComment getStrategyComment(int id);

    public PageHolder<StrategyComment> queryStrategyCommentByPage(int strategyId,int userId, int pageIndex, int pageSize);

    public String findStrategyCommentList(int strategyId,int pageIndex, int pageSize);

    public int updateCommentStatus(int id, int status);

    public int saveStrategyScore(StrategyScore strategyScore);

    public StrategyScore getStrategyScore(int strategyId);

    public int saveStrategy(Strategy strategy);

    public int deleteStrategy(int id);

    public int updateStrategy(Strategy strategy);

    public Strategy getStrategy(int id);

    public PageHolder<Strategy> queryStrategyByPage(int id,int type,int displayStatus,int runStatus,int pageIndex,int pageSize);

    public int updateStrategyStatus(int id, int status);

    public int saveStrategyUser(StrategyUser strategy);

    /**
     * 延长策略使用时间
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @param days       延长天数
     * @return
     */
    public int expandExpireTime(int userId, int strategyId, int days,String payType,double price);

    public StrategyUser getStrategyUser(int id);

    /**
     * 查询用户名下的策略列表
     *
     * @param userId 用户ID
     * @param expire 1-未过期策略；0-全部策略
     * @return
     */
    public String findStrategyByUserId(int userId, int expire);

    /**
     * 查询用户名下的策略列表
     * @param userId 用户ID
     * @param expire 1-未过期策略；0-全部策略
     * @return
     */
    public List<StrategyUser> findStrategyUserByUserId(int userId, int expire);

    public PageHolder<StrategyUser> queryStrategyUserByPage(int userId,int strategyId, int pageIndex, int pageSize);

    public int batchSaveStrategyUserStock(int userId, int strategyId,String relationList);

    public String findStockByStrategyAndUser(int userId, int strategyId);

    public int saveUserLoop(int userId);

    public int incrLoopNum(int userId);

    public int incrFinishNum(int userId,int strategyId,String stocks);

    public UserLoop findUserLoopByUserId(int userId);

    public int batchInsertStrategyStockOneProfit(List<StrategyStockOneProfit> list);

    public int truncateStrategyStockOneProfit(int strategyId);

    public List<StrategyStockOneProfit> queryStrategyStockOneProfit(int strategyId, String... stocks);

    public int batchInsertStrategyStockThreeProfit(List<StrategyStockThreeProfit> list);

    public int truncateStrategyStockThreeProfit(int strategyId);

    public List<StrategyStockThreeProfit> queryStrategyStockThreeProfit(int strategyId, String... stocks);

    public StrategyStockOneProfit findStrategyStockOneProfit(int strategyId, String stockCode);

    public AverageProfit getOneYearAverageProfit(int strategyId, String... stocks);

    public AverageProfit getThreeYearAverageProfit(int strategyId, String... stocks);
    public int saveStrategyStockBlacklist(StrategyStockBlacklist ssb);

    public List<StrategyStockBlacklist> getStrategyStockBlacklist(int strategyId);

    public int updateStockBlack(int id,String stockCodes);

    public int sendTradeMq(int type, String json);

    public String getPermissionNum(int userId,int strategyId);

    /**
     * 添加推荐/热门股票
     * @param recommendHotStock
     * @return
     */
    int addRecommendHotStock(RecommendHotStock recommendHotStock);

    /**
     * 批量添加推荐/热门股票
     * @param list
     * @return
     */
    int batchAddRecommendHotStock(List<RecommendHotStock> list);

    /**
     * 根据策略ID删除推荐/热门股票
     * @param strategyId
     * @return
     */
    int delRecommendHotStock(int strategyId);

    /**
     * 获取推荐/热门股票
     * @param strategyId
     * @param type
     * @return
     */
    List<RecommendHotStock> getRecommendHotStockList(int strategyId,int type);

    int delRecommendHotStockByType(int type, int strategyId);

    public String queryStrategyReport(int strategyId, String stocks,int userId);

    public int deleteAllUserLoop();

    public List<StrategyUser> findStockByStrategyAndStock(int strategyId, String stockCode);

    /**
     * 异步开通策略权限
     * @param userId
     * @param strategyId
     * @param day
     * @return
     */
    public int dredgeStrategyAuthByMq(int userId, int strategyId, int day,String payType,double price);

    public Strategy findStrategyByCategoryId(String categoryId);

    public List<Integer> findAllRunStrategyId();

    public StrategyUser getStrategyUserByUidSid(int userId, int strategyId);

    public int heartBeat();

    public String disposeQuantStk(int strategyId, Date time);

    /**
     * 判断该策略是否有数据文件
     * @param strategyId
     * @return
     */
    public boolean isExitsFile(int strategyId);

    /**
     * 清理该策略的数据文件，清理完成后才能重新“回测”
     * @param strategyId
     * @return
     */
    public boolean clearStrategyData(int strategyId);

    public List<StrategyUserStock> findAllValidRelation();

    /**
     *  统计当前付费用户
     * @param time
     * @return
     */
    public String findStrategyBuyUserCount(String time);

    /**
     *  统计用户回测总体使用情况
     * @param time
     * @return
     */
    public String findStrategyUserLoopCount(String time);

    /**
     *  根据策略ID和数量查询最优股票列表
     * @param sid
     * @param limit
     * @return
     */
    public List<StrategyBestStock> queryStrategyBestStockListBySid(int sid, int limit);

    /**
     * 关注股票
     * @param userId
     * @param strategyId
     * @param stockCode
     * @return
     */
    public int saveStrategyUserStock(int userId, int strategyId, String stockCode);

    /**
     * 取消关注股票
     * @param userId
     * @param strategyId
     * @param stockCode
     * @return
     */
    public int deleteStrategyUserStock(int userId, int strategyId, String stockCode);

    /**
     * 查询关注股票数量
     * @param userId
     * @param strategyId
     * @return
     */
    public int selectFollowNum(int userId, int strategyId);

    public StockSearchResponse searchStockResult(int userId, int strategyId, String stockCode);

    /**
     * 查询今日交易
     * @param strategyId
     * @param type 1 买入 2卖出
     * @param seq
     * @param num
     * @return
     */
    public List<StockTradeToday> queryTodayTrade(int strategyId, int type, long seq, int num);

    /**
     * 添加今日数据
     * @param strategyId
     * @param stockTradeToday
     * @return
     */
    int addTodayTrade(int strategyId, StockTradeToday stockTradeToday);

    /**
     * 清理股票今日数据
     * @param strategyId
     * @param positionNum
     * @return
     */
    int clearTodayTrade(int strategyId, int positionNum);

    /**
     * 修改今日买入盈利率
     * @param strategyId
     * @param stockTradeTodayList
     * @return
     */
    int updateTodayBuy(int strategyId, List<StockTradeToday> stockTradeTodayList);

    /**
     * 增加近五日买入
     * @param strategyId
     * @param stockTradeNear5DaysList
     * @return
     */
    int addStockTradeNear5Days(int strategyId, List<StockTradeNear5days> stockTradeNear5DaysList);


    /**
     * 清理近五日买入
     * @param strategyId
     * @return
     */
    int clearNear5DaysTrade(int strategyId);

    /**
     * 查询近五日买入
     * @param strategyId
     * @param seq
     * @param num
     * @return
     */
    public List<StockTradeNear5days> queryDays5Trade(int strategyId, long seq, int num);

    StrategyExtend getStrategyExtend(int strategyId);

    /**
     * 查询用户关注股票代码，8位用,分割
     * @param userId
     * @param strategyId
     * @return
     */
    public String findUserFollowStock(int userId, int strategyId);

    public String findReportData(int day,double lt,double rt,int num);

    public List<Integer> findAllStrategyUser(String time);

    public StrategySearch searchStrategyBestStock();

    /**
     * 增加dna的权限
     * @param userId
     * @return
     */
    int incrDNALoopNum(int userId);

    /**
     * 查询用户DNA的权限
     * @param userId
     * @return
     */
    DNAAcl findDNALoopByUserId(int userId);

    /**
     * 查询自选股交易记录
     * @param userId
     * @param strategyId
     * @param type 1-全部自选股；2-今日有交易的自选股
     * @return
     */
    List<StrategyPortfolio> queryStrategyPortfolioList(int userId, int strategyId, int type);

    /**
     * 自选股涨跌概率
     * @param userId
     * @param strategyId
     * @return
     */
    List<StrategyPortfolioForecast> forecastPortfolioList(int userId, int strategyId);
 }
