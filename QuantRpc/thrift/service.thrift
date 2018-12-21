namespace java com.youguu.quant.rpc.thrift.gen

struct StrategyBestStockThrift{
    1:i32 strategyId;
    2:string stockCode;
    3:string stockName;
    4:string findTime;
    5:double profit;
    6:i64 updateTime;
}

struct StrategyCommentThrift{
    1:i32 id;
    2:i32 strategyId;
    3:i32 userId;
    4:string cotent;
    5:i64 createTime;
}

struct StrategyScoreThrift{
    1:i32 successNum;
    2:double averageProfit;
    3:i64 updateTime;
    4:i32 strategyId;
}

struct StrategyThrift{
    1:i32 id;
    2:string name;
    3:string logo;
    4:string description;
    5:i32 type;
    6:string productId;
    7:string ratingLabel;
    8:i32 backTimes;
    9:i32 status;
    10:i64 updateTime;
    11:i64 createTime;
    12:string remark;
}

struct StrategyUserThrift{
    1:i32 id;
    2:i32 userId;
    3:i32 strategId;
    4:i64 buyTime;
    5:i64 expireTime;
    6:i64 updateTime;
    7:i64 createTime;
}

struct StrategyUserStockThrift{
    1:i32 id;
    2:i32 userId;
    3:i32 strategId;
    4:string stockCode;
    5:string marketId;
    6:i64 updateTime;
    7:i64 createTime;
}

struct UserLoopThrift{
    1:i32 id;
    2:i32 userId;
    3:i64 loopDate;
    4:i32 loopNum;
    5:i32 finishNum;
    6:i64 updateTime;
    7:i64 createTime;
}

struct StrategyStockOneProfitThrift{
    1:i32 id;
    2:i32 strategyId;
    3:string stockCode;
    4:double profit;
    5:i64 createTime;
}

struct StrategyStockThreeProfitThrift{
    1:i32 id;
    2:i32 strategyId;
    3:string stockCode;
    4:double profit;
    5:i64 createTime;
}


service SignalServiceThriftRpcService {
    /**
     * 查询某策略下所有股票文件名称(path+filename)
     * @param strategyId 策略ID
     * @return
     */
    string loadStockFileName(1:i32 strategyId);

    /**
     * 加载交易信号
     * @param strategyId
     * @param stockCode
     * @return 每个股票一个信号List  key:股票代码,value:信号列表
     */
    string loadTradeSignal(1:i32 strategyId, 2:string stocks);

    /**
     * 加载交易信号
     */
    string loadTradeSignalDays(1:i32 strategyId, 2:i32 days, 3:string stocks);

    string loadTradeSignalSection(1:i32 strategyId, 2:i64 startDate, 3:i64 endDate, 4:string stockCode)


    string getLastTradeSignal(1:i32 strategyId, 2:string stockCode)

    string readOneTradeSignal(1:i32 strategyId, 2:string stockCode)

    /**
     * 查询交易记录
     * @param strategyId
     * @param stockCode
     * @return 每个股票一个信号List  key:股票代码,value:信号列表
     */
    string queryPageStrategyTradeRecord(1:i32 strategyId, 2:i32 userId, 3:i32 type, 4:i32 pageIndex, 5:i32 pageSize, 6:string stocks);
    /** 查询用户购买的某策略下所有股票最新的交易信号*/
    string queryStockRealtimeSignalList(1:i32 strategyId,2:i32 userId);
    /** 查询N只股票的平均盈利曲线*/
    string queryAverageProfitCurve(1:i32 strategyId, 2:i32 days, 3:string stocks);
    /** 查询N只股票总收益率*/
    string querySumProfit(1:i32 strategyId,2:i32 days,3:i32 type, 4:string stocks);

    string querySumProfitNew(1:i32 strategyId, 2:i64 startDate, 3:i64 endDate, 4:i32 type, 5:string stocks);

    /** 心态监测 **/
    i32 heartBeat();

    string deleteSignalFile(1:i32 strategyId);

    string countProfit(1:i32 strategyId,2:i64 date);

    string countfiveDay(1:i32 strategyId, 2:double five);

    string countNowHoldStock(1:i32 strategyId, 2:double now);

    i32 disReportData(1:i32 strategyId);

    /**
     * 根据股票代码查询持有该股票且收益最高的机器人信息
     * @param stockCode 股票代码
     * @return
     */
    string findDnaStockHold(1:string stockCode, 2:i32 userId);


    string getLastTradeSignalByDate(1:i32 strategyId,2:i32 statDate, 3:string stockCode)


    string getLastBuyTradeSignalByDate(1:i32 strategyId, 2:i32 statDate, 3:string stockCode)

}

service StrategyServiceThriftRpcService {

    i32 saveStrategyBestStock(1:string stockList);

    i32 updateStrategyBestStock(1:string stockList);

    string queryStrategyBestStock(1:i32 num);

    string queryAllStrategyBestStock(1:i32 strategyId);

    string queryAllStrategy(1:i32 userId);

    i32 saveStrategyComment(1:string strategy);

    i32 deleteStrategyComment(1:i32 id);

    i32 updateStrategyComment(1:string strategy);

    string getStrategyComment(1:i32 id);

    string queryStrategyCommentByPage(1:i32 strategyId,2:i32 userId,3:i32 pageIndex,4:i32 pageSize);

    string findStrategyCommentList(1:i32 strategyId,2:i32 pageIndex,3:i32 pageSize);

    i32 updateCommentStatus(1:i32 id,2:i32 status);

    i32 saveStrategyScore(1:string strategyScore);

    string getStrategyScore(1:i32 strategyId);


    i32 saveStrategy(1:string strategy);

    i32 deleteStrategy(1:i32 id);

    i32 updateStrategy(1:string strategy);

    string getStrategy(1:i32 id);

    string queryStrategyByPage(1:i32 id,2:i32 type,3:i32 displayStatus,4:i32 runStatus,5:i32 pageIndex,6:i32 pageSize);

    i32 updateStrategyStatus(1:i32 id, 2:i32 status);


    i32 saveStrategyUser(1:string strategy);

    i32 expandExpireTime(1:i32 userId, 2:i32 strategyId, 3:i32 days,4:string payType,5:double price);

    string getStrategyUser(1:i32 id);

    string findStrategyByUserId(1:i32 userId, 2:i32 expire);

    string findStrategyUserByUserId(1:i32 userId, 2:i32 expire);

    string queryStrategyUserByPage(1:i32 userId,2:i32 strategyId,3:i32 pageIndex,4:i32 pageSize);

    i32 batchSaveStrategyUserStock(1:i32 userId, 2:i32 strategyId,3:string relationList);

    string findStockByStrategyAndUser(1:i32 userId, 2:i32 strategyId);

    string queryStrategyPortfolioList(1:i32 userId, 2:i32 strategyId, 3:i32 type);

    string forecastPortfolioList(1:i32 userId, 2:i32 strategyId);

    i32 saveUserLoop(1:i32 userId);

    i32 deleteAllUserLoop();

    i32 incrLoopNum(1:i32 userId);

    i32 incrFinishNum(1:i32 userId,2:i32 strategyId,3:string stocks);

    string findUserLoopByUserId(1:i32 userId);

    i32 batchInsertStrategyStockOneProfit(1:list<StrategyStockOneProfitThrift> thriftList);

    i32 truncateStrategyStockOneProfit(1:i32 strategyId);

    string queryStrategyStockOneProfit(1:i32 strategyId, 2:string stocks);

    i32 batchInsertStrategyStockThreeProfit(1:list<StrategyStockThreeProfitThrift> thriftList);

    i32 truncateStrategyStockThreeProfit(1:i32 strategyId);

    string queryStrategyStockThreeProfit(1:i32 strategyId, 2:string stocks);

    string findStrategyStockOneProfit(1:i32 strategyId, 2:string stockCode);

    string getOneYearAverageProfit(1:i32 strategyId, 2:string stocks);

    string getThreeYearAverageProfit(1:i32 strategyId, 2:string stocks);

    i32 saveStrategyStockBlacklist(1:string ssb);

    string getStrategyStockBlacklist(1:i32 strategyId);

    i32 updateStockBlack(1:i32 id,2:string stockCodes);

    i32 sendTradeMq(1:i32 type, 2:string json);

    string getPermissionNum(1:i32 userId,2:i32 strategyId);

    /** 添加推荐、热门股票*/
    i32 addRecommendHotStock(1:string recommendHotStock);
    /** 批量添加推荐、热门股票*/
    i32 batchAddRecommendHotStock(1:string recommendHotStockList);
    /** 根据策略ID删除推荐、热门股票*/
    i32 delRecommendHotStock(1:i32 strategyId);
    /** 获取推荐、热门股票*/
    string getRecommendHotStockList(1:i32 strategyId,2:i32 type);

    string queryStrategyReport(1:i32 strategyId, 2:string stocks,3:i32 userId);

    string findStockByStrategyAndStock(1:i32 strategyId, 2:string stockCode);

    i32 delRecommendHotStockByType(1:i32 type, 2:i32 strategyId);

    /** 开通策略权限*/
    i32 dredgeStrategyAuthByMq(1:i32 userId,2:i32 strategyId,3:i32 day,4:string payType,5:double price);

    string findStrategyByCategoryId(1:string categoryId);

    string findAllRunStrategyId();

    string getStrategyUserByUidSid(1:i32 userId,2:i32 strategyId);

    /** 心态监测 **/
    i32 heartBeat();

    string disposeQuantStk(1:i32 strategyId, 2:string time)

    /** 判断策略是否有数据文件 **/
    bool isExitsFile(1:i32 strategyId)

    /** 清理策略数据 **/
    bool clearStrategyData(1:i32 strategyId)

    string findAllValidRelation();

    string findStrategyBuyUserCount(1:string time);

    string findStrategyUserLoopCount(1:string time);

    string queryStrategyBestStockListBySid(1:i32 sid,2:i32 limit)

    /** 关注股票*/
    i32 saveStrategyUserStock(1:i32 userId, 2:i32 strategyId, 3:string stockCode);

    /** 取消关注股票*/
    i32 deleteStrategyUserStock(1:i32 userId, 2:i32 strategyId, 3:string stockCode);

    /** 查询关注股票数量*/
    i32 selectFollowNum(1:i32 userId, 2:i32 strategyId);

    /** 查询今日买入和卖出
     *  @param strategyId 策略id
     *  @param type 1-当日买入；2-当日卖出
     *  @param seq 序号（使用最后一条的rank值）
     *  @param num 请求的记录数
     **/
    string queryTodayTrade(1:i32 strategyId,2:i32 type,3:i64 seq,4:i32 num);

    /** 添加今日买入和卖出的数据 -- 同步操作当日买入卖出数量
     *  @param strategyId 策略id
     *  @param stockTradeToday 完整的json对象
     **/
    i32 addTodayTrade(1:i32 strategyId,2:string stockTradeToday);

    /** 凌晨清理今天的数据 清空买入卖出 ， 清0买入卖出数量 重新计算持仓数量
     *  @param strategyId策略id
     *  @param positionNum当前持仓数量
     **/
    i32 clearTodayTrade(1:i32 strategyId,2:i32 positionNum);

    /** 修改当日买入的盈利率 **/
    i32 updateTodayBuy(1:i32 strategyId,2:string stockTradeTodayList);

    /** 增加近五日买入的股票 **/
    i32 addStockTradeNear5Days(1:i32 strategyId,2:string stockTradeNear5DaysList);

    /** 清空近五日买入 **/
    i32 clearNear5DaysTrade(1:i32 strategyId);

    /** 查询近五日买入
     *  @param strategyId 策略id
     *  @param seq 序号（使用最后一条的rank值）
     *  @param num 请求的记录数
     **/
    string queryDays5Trade(1:i32 strategyId,2:i64 seq,3:i32 num);

    /** 搜索股票结果页面*/
    string searchStockResult(1:i32 userId, 2:i32 strategyId, 3:string stockCode);

    string getStrategyExtend(1:i32 strategyId)

    string findUserFollowStock(1:i32 userId, 2:i32 strategyId);

    string searchStrategyBestStock();

    string findReportData(1:i32 day,2:double lt,3:double rt,4:i32 num);

    string findAllStrategyUser(1:string time);

    i32 incrDNALoopNum(1:i32 userId);

    string findDNALoopByUserId(1:i32 userId);
}

service KLineServiceThriftRpcService {
    /** 心态监测 **/
    i32 heartBeat();
    /** 查询某只股票 相似度前一百的股票*/
    string findKLineSimById(1:string id,2:i32 userId);
    /** 保存某只股票相似度前一百的股票*/
    string saveKlineSimResult(1:string klinesim);
}
