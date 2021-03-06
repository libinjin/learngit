package com.youguu.quant.rpc.client.signal;

import com.alibaba.fastjson.JSONObject;
import com.youguu.quant.signal.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/30.
 */
public interface ISignalRpcService {
    /**
     * 查询某策略下所有股票文件名称(path+filename)
     * @param strategyId 策略ID
     * @return
     */
    public List<String> loadStockFileName(int strategyId);

    /**
     * 加载交易信号
     * @param strategyId
     * @param stockCode
     * @return 每个股票一个信号List  key:股票代码,value:信号列表
     */
    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, String... stockCode);

    /**
     * 加载N个交易日的交易信号
     * @param strategyId
     * @param days
     * @param stockCode
     * @return
     */
    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, int days, String... stockCode);


    public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, long startDate, long endDate, String... stockCode);

    /**
     * 获取最后一个交易信号返回
     * @param strategyId
     * @param stockCode
     * @return
     */
    public TradeSignal getLastTradeSignal(int strategyId, String stockCode);

    public TradeSignal getLastBuyTradeSignal(int strategyId, String stockCode);

    /**
     * 查询策略成交记录
     * @param strategyId
     * @param pageIndex
     * @param pageSize
     * @param stocks
     * @return
     */
    public List<StrategyTradeRecord> queryPageStrategyTradeRecord(int strategyId, int userId, int type, int pageIndex, int pageSize, String stocks);

    /**
     * 查询用户购买的某策略下所有股票最新的交易信号
     * @param strategyId
     * @param userId
     * @return
     */
    public Map<String,StockRealtimeSignal> queryStockRealtimeSignalList(int strategyId, int userId);

    /**
     * 查询N只股票的平均盈利曲线
     * @param strategyId 策略ID
     * @param days 天数
     * @param stocks 证券代码数组
     * @return
     */
    public List<ProfitCurve> queryAverageProfitCurve(int strategyId,int days, String stocks);

    /**
     * 查询N只股票总收益率
     * @param strategyId
     * @param days
     * @param type 0.全部 1.正
     * @param stocks
     * @return
     */
    public List<SumProfit> querySumProfit(int strategyId,int days,int type, String stocks);

    public List<SumProfit> querySumProfitNew(int strategyId, long startDate, long endDate, int type, String stocks);

    public int heartBeat();

    /**
     * 删除策略信号文件，重新回测时调用
     * @param strategyId
     * @return
     */
    public JSONObject deleteSignalFile(int strategyId);

    /**
     * 统计策略盈利情况
     * @param strategyId
     * @param date
     * @return
     */
    public String countProfit(int strategyId,Date date);

    /**
     *  查询当日和近五日买入股票累计盈利大于five的股票
     * @param strategyId
     * @param five
     * @return
     */
    public List<Stock5dayAndNowHold> countfiveDay(int strategyId,double five);

    /**
     *  查询当前所有持仓股票累计盈利大于now的股票
     * @param strategyId
     * @param now
     * @return
     */
    public List<Stock5dayAndNowHold> countNowHoldStock( int strategyId,double now);

    public int disReportData(int strategyId);

    /**
     * 根据股票代码查询持有该股票且收益最高的机器人信息
     * @param stockCode 股票代码
     * @return
     */
    public DnaStockHold findDnaStockHold(String stockCode, int userId);


    /**
     * 获取<=指定日期的最后一个信号
     * @param strategyId
     * @param date  yyyyMMdd
     * @param stockCode
     * @return
     */
    public TradeSignal getLastTradeSignal(int strategyId,int date , String stockCode);


    /**
     * 获取指定日期之前最后一个买入交易信号返回,不包含指定日期为买入的信号
     * @param strategyId
     * @param date   yyyyMMdd
     * @param stockCode
     * @return
     */
    public TradeSignal getLastBuyTradeSignal(int strategyId,int date, String stockCode);

}
