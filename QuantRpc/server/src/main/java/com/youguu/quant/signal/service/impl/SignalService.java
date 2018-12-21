package com.youguu.quant.signal.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youguu.akka.actor.ISyncHandler;
import com.youguu.akka.actor.SyncCall;
import com.youguu.akka.pojo.ResPonse;
import com.youguu.asteroid.rpc.client.AsteroidRPCClientFactory;
import com.youguu.asteroid.rpc.client.tradeday.ITradeDayRPCService;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.base.ExcelUtil;
import com.youguu.quant.base.QuantTradePlateConfig;
import com.youguu.quant.quote.QuoteUtil;
import com.youguu.quant.rpc.common.Constants;
import com.youguu.quant.signal.pojo.*;
import com.youguu.quant.signal.service.ISignalService;
import com.youguu.quant.strategy.pojo.*;
import com.youguu.quant.strategy.service.*;
import com.youguu.quant.util.FileUtil;
import com.youguu.quote.pojo.CurStatus;
import com.youguu.quote.pojo.KLine;
import com.youguu.quote.pojo.StockInfo;
import com.youguu.quote.rpc.client.QuoteClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SomeBody on 2016/8/23.
 */
@Service("signalService")
public class SignalService implements ISignalService {
	private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_SERVER);
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");

	@Resource
	private IStrategyUserStockService strategyUserStockService;

	private ITradeDayRPCService tradeDayService = AsteroidRPCClientFactory.getTradeDayRPCService();

	@Resource
	private IStrategyUserService strategyUserService;
	ITradeDayRPCService tradeDayRPCService = AsteroidRPCClientFactory.getTradeDayRPCService();

	@Resource
	private IStrategyService strategyService;

	@Resource
	private IStockTradeTodaySerive stockTradeTodaySerive;

	@Resource
	private IStockTradeNear5daysService stockTradeNear5daysService;

	@Resource
	private IStrategyLimitUpService strategyLimitUpService;
	@Resource
	private IStrategyStockOneProfitService strategyStockOneProfitService;
	@Override
	public List<StrategyTradeRecord> queryPageStrategyTradeRecord(int strategyId, int userId, int type, final int
			pageIndex, final int pageSize, String... stocks) {
		try {
			SyncCall sync = new SyncCall();

			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;

			final List<TradeSignal> totalList = Collections.synchronizedList(new ArrayList<TradeSignal>());
			List<StrategyTradeRecord> tradeRecords = new ArrayList<>();
			Map<String, Object> buyMap = new HashMap<>();//买入记录

			//是否过期，过期展示数据延迟5个交易日
			final boolean expire = strategyUserService.isExpire(userId, strategyId);
			int endDate = 0;
			if (expire) {
				endDate = Integer.valueOf(tradeDayRPCService.nextTradeDay(new Date(), -6));
			} else if (!expire && type == 2) {
				endDate = Integer.valueOf(tradeDayRPCService.nextTradeDay(new Date(), -1));
			} else {
				endDate = Integer.valueOf(yyyyMMdd.format(new Date()));
			}
			final int finalEndDate = endDate;
			for (final String stock : stocks) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stock + ".dat";
						try {
							List<TradeSignal> list = FileUtil.read(filePath, pageIndex, pageSize, false, finalEndDate);
							if (list != null) {
								totalList.addAll(list);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
				buyMap.put(stock, -10);
			}
			sync.sync(60);

			Collections.sort(totalList);

			for (int i = pageIndex * pageSize - 1; i >= (pageIndex - 1) * pageSize; i--) {
				if (i >= totalList.size())
					continue;

				StrategyTradeRecord str = new StrategyTradeRecord();
				TradeSignal ts = totalList.get(i);

				String direct = ts.getDirect();
				String rate = "0";

				str.setStockCode(ts.getStockcode());
				str.setStockName(getStockName(ts.getStockcode()));
				str.setPrice(ts.getPriceFormat());
				str.setDirection(direct);
				str.setDate(String.valueOf(ts.getDatetime()));

				//计算收益率
				if (direct.equals("B")) {
					buyMap.put(ts.getStockcode(), ts.getPrice());
				} else if (direct.equals("S")) {
					int buyPrice = (Integer) buyMap.get(ts.getStockcode());
					if (buyPrice == -10) {
						//需要去查询上一条买入的记录
						String filePath = basePath + "/" + ts.getStockcode() + ".dat";
						TradeSignal buyTradeSignal = getLastTimeBuyTradeSignal(filePath, ts.getDatetime());
						if (buyTradeSignal != null) {
							buyMap.put(ts.getStockcode(), buyTradeSignal.getPrice());
							buyPrice = buyTradeSignal.getPrice();
						}
					}
					if (buyPrice != -10) {
						BigDecimal b = new BigDecimal((ts.getPrice() - buyPrice) / new Double(buyPrice) * 100);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						rate = String.valueOf(f1);
					}

				}
				str.setProfitRate(rate);
				tradeRecords.add(str);
			}
			Collections.reverse(tradeRecords);

			return tradeRecords;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, StockRealtimeSignal> queryStockRealtimeSignalList(int strategyId, int userId) {

		try {
			final Map<String, StockRealtimeSignal> map = new java.util.concurrent.ConcurrentHashMap<>();
			SyncCall sync = new SyncCall();
			List<StrategyUserStock> stockList = strategyUserStockService.findStockByStrategyAndUser(userId,
                    strategyId);

			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;

			for (final StrategyUserStock stock : stockList) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stock.getStockCode() + ".dat";

						try {
							int length = FileUtil.getFileLength(filePath);
							int position = length - TradeSignal.LENGTH;
							while (true) {
								TradeSignal ts = FileUtil.readOneTradeSignal(filePath, position < 0 ? 0 : position);
								if (!"H".equals(ts.getDirect())) {
									StockRealtimeSignal srs = new StockRealtimeSignal();
									srs.setStockCode(ts.getStockcode());
									srs.setStockName(getStockName(ts.getStockcode()));
									srs.setDate(String.valueOf(ts.getDatetime()));
									srs.setDirection(ts.getDirect());
									srs.setPrice(ts.getPriceFormat());
									map.put(ts.getStockcode(), srs);
									break;
								}
								position = position - TradeSignal.LENGTH;
								if (position < 0) {
									break;
								}
							}
						} catch (Exception e) {
							logger.error("queryStockRealtimeSignalList syncHandler error", e);
						}
						return null;
					}
				});
			}
			sync.sync();
			return map;
		} catch (Exception e) {
			logger.error("queryStockRealtimeSignalList error", e);
		}
		return null;
	}

	@Override
	public Map<String, StockRealtimeSignal> queryStockRealtimeSignalList(int strategyId, String[] stockCodes) {
		try {
			final Map<String, StockRealtimeSignal> map = new java.util.concurrent.ConcurrentHashMap<>();
			SyncCall sync = new SyncCall();

			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;

			for (final String stockCode : stockCodes) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stockCode + ".dat";
						try {
							int length = FileUtil.getFileLength(filePath);
							int position = length - TradeSignal.LENGTH;
							while (true) {
								TradeSignal ts = FileUtil.readOneTradeSignal(filePath, position < 0 ? 0 : position);
								if (!"H".equals(ts.getDirect())) {
									StockRealtimeSignal srs = new StockRealtimeSignal();
									srs.setStockCode(ts.getStockcode());
									srs.setStockName(getStockName(ts.getStockcode()));
									srs.setDate(String.valueOf(ts.getDatetime()));
									srs.setDirection(ts.getDirect());
									srs.setPrice(ts.getPriceFormat());
									map.put(ts.getStockcode(), srs);
									break;
								}
								position = position - TradeSignal.LENGTH;
								if (position < 0) {
									break;
								}
							}
						} catch (Exception e) {
							logger.error("queryStockRealtimeSignalList syncHandler error", e);
						}
						return null;
					}
				});
			}
			sync.sync();
			return map;
		} catch (Exception e) {
			logger.error("queryStockRealtimeSignalList error", e);
		}
		return null;
	}

	/**
	 * 小数相除,四舍五入进位
	 *
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	private String divide(double arg1, double arg2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
		BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 小数相除,四舍五入进位
	 *
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	private double divideDouble(double arg1, double arg2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
		BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public List<String> loadStockFileName(int strategyId) {
		String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
		basePath += "/" + strategyId;
		return FileUtil.readFileName(basePath);
	}

	@Override
	public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, String... stockCode) {
		return loadTradeSignal(strategyId, -1, stockCode);
	}

	@Override
	public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, final int days, String... stockCode) {
		final Map<String, List<TradeSignal>> map = new java.util.concurrent.ConcurrentHashMap<>();
		SyncCall sync = new SyncCall();
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			for (final String stock : stockCode) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stock + ".dat";
						int length = FileUtil.getFileLength(filePath);
						int position = length - days * 2 * TradeSignal.LENGTH; //每天最多2条数据
						position = position < 0 ? 0 : position;
						List<TradeSignal> list = null;
						List<TradeSignal> result = new ArrayList<>();
						String temp_dateTime = "";
						int dayCount = 1;
						try {
							list = FileUtil.read(filePath, days == -1 ? 0 : position, -1);
							for (int i = list.size() - 1; i >= 0; i--) {
								TradeSignal tradeSignal = list.get(i);
								if (temp_dateTime.equals("")) {
									temp_dateTime = String.valueOf(tradeSignal.getDatetime());
								}
								if (!temp_dateTime.equals(String.valueOf(tradeSignal.getDatetime()))) {
									temp_dateTime = String.valueOf(tradeSignal.getDatetime());
									dayCount++;
								}
								result.add(tradeSignal);
								if (dayCount >= days) {
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						map.put(stock, result);
						return null;
					}
				});
			}
			sync.sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, List<TradeSignal>> loadTradeSignal(int strategyId, final long startDate, final long endDate,
                                                          String... stockCode) {
		final Map<String, List<TradeSignal>> map = new ConcurrentHashMap<>();
		SyncCall sync = new SyncCall();
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			for (final String stock : stockCode) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stock + ".dat";
						List<TradeSignal> list = FileUtil.readInterval(filePath, startDate, endDate);
						if (list != null && list.size() > 0) {
							map.put(stock, list);
						}
						return null;
					}
				});
			}
			sync.sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<ProfitCurve> queryAverageProfitCurve(int strategyId, int days, String... stocks) {

		DecimalFormat df = new DecimalFormat("####0.0000");
		List<ProfitCurve> result = new ArrayList<>();
		Map<String, Double> netMap = new ConcurrentHashMap<>();

		Map<String, List<TradeSignal>> map = this.loadTradeSignal(strategyId, days, stocks);

		for (List<TradeSignal> tradeSignalList : map.values()) {
			boolean power = true;
			double net = 1;//净值初始为1
			double lastClosePrice = 0.00;

			for (int i = 0; i < tradeSignalList.size(); i++) {

				TradeSignal ts = tradeSignalList.get(i);
				if (i == 0) {
					lastClosePrice = ts.getCloseprice();
					continue;
				}

				String date = String.valueOf(ts.getDatetime());
				String direct = ts.getDirect();

				if (power && !direct.equals("S")) {
					power = false;
				}
				if (!power) {
					if (direct.equals("B")) {
						net = net + net * ((ts.getCloseprice() - ts.getPrice()) / new Double(ts.getPrice()));
					} else if (direct.equals("H")) {
						net = net + net * ((ts.getCloseprice() - lastClosePrice) / new Double(lastClosePrice));
					} else if (direct.equals("S")) {
						net = net + net * ((ts.getPrice() - lastClosePrice) / new Double(lastClosePrice));
					}
					double sum = netMap.containsKey(netMap) ? netMap.get(date) : 0;
					netMap.put(date, sum + net);
				}
				lastClosePrice = ts.getCloseprice();
			}

		}

		for (String key : netMap.keySet()) {
			ProfitCurve profitCurve = new ProfitCurve();
			profitCurve.setDate(key);
			profitCurve.setProfitRate(Double.parseDouble(df.format((netMap.get(key) - stocks.length) / stocks
                    .length)));
			result.add(profitCurve);
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public List<SumProfit> querySumProfit(int strategyId, int days, int type, String... stocks) {
		List<SumProfit> result = new ArrayList<>();
		Map<String, Double> netMap = new ConcurrentHashMap<>();

		Map<String, List<TradeSignal>> map = this.loadTradeSignal(strategyId, days, stocks);

		for (String key : map.keySet()) {
			List<TradeSignal> tradeSignalList = map.get(key);
			Collections.reverse(tradeSignalList);
			boolean power = true;
			double net = 1;//净值初始为1
			String code = "";
			double lastClosePrice = 0.00;

			for (int i = 0; i < tradeSignalList.size(); i++) {
				TradeSignal ts = tradeSignalList.get(i);
				if (i == 0) {
					lastClosePrice = ts.getCloseprice();
					continue;
				}
				code = ts.getStockcode();
				String direct = ts.getDirect();

				if (power && !direct.equals("S")) {
					power = false;
				}
				if (!power) {
					double profit = 0.00;
					if (direct.equals("B")) {
						profit = net * ((ts.getCloseprice() - ts.getPrice()) / new Double(ts.getPrice()));
					} else if (direct.equals("H")) {
						profit = net * ((ts.getCloseprice() - lastClosePrice) / new Double(lastClosePrice));
					} else if (direct.equals("S")) {
						profit = net * ((ts.getPrice() - lastClosePrice) / new Double(lastClosePrice));
					}
					if (type == 1 && profit < 0) {

					} else {
						net = net + profit;
					}
				}
				lastClosePrice = ts.getCloseprice();
			}
			netMap.put(key, net - 1);
		}

		for (String key : netMap.keySet()) {
			SumProfit sumProfit = new SumProfit();
			sumProfit.setStockCode(key);
			sumProfit.setStrategyId(strategyId);
			BigDecimal b = new BigDecimal(netMap.get(key));
			sumProfit.setProfitRate(b.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
			result.add(sumProfit);
		}
		return result;
	}

	@Override
	public TradeSignal getLastTradeSignal(int strategyId, String stockCode) {
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			String filePath = basePath + "/" + stockCode + ".dat";
			TradeSignal ts = FileUtil.read(filePath);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TradeSignal getLastBuyTradeSignal(int strategyId, String stockCode) {
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			String filePath = basePath + "/" + stockCode + ".dat";
			int length = FileUtil.getFileLength(filePath);
			int postion = length - TradeSignal.LENGTH * 2;
			TradeSignal ts = null;
			while (true) {
				ts = FileUtil.readOneTradeSignal(filePath, postion);
				if ("B".equals(ts.getDirect())) {
					break;
				}
				postion = postion - 25;
				if (postion < 0) {
					break;
				}
			}

			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private TradeSignal getLastBuyTradeSignal2(int strategyId, String stockCode) {
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			String filePath = basePath + "/" + stockCode + ".dat";
			int length = FileUtil.getFileLength(filePath);
			int postion = length - TradeSignal.LENGTH * 1;
			TradeSignal ts = null;
			while (true) {
				ts = FileUtil.readOneTradeSignal(filePath, postion);
				if (ts != null) {
					if ("B".equals(ts.getDirect())) {
						break;
					}
				}
				postion = postion - 25;
				if (postion < 0) {
					break;
				}
			}

			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取每只股票最后一个非H信号
	 *
	 * @param strategyId
	 * @param stocks
	 * @return
	 */
	@Override
	public List<TradeSignal> getLastTradeSignalList(int strategyId, String... stocks) {
		try {
			SyncCall sync = new SyncCall();

			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;

			final List<TradeSignal> totalList = Collections.synchronizedList(new ArrayList<TradeSignal>());

			for (final String stock : stocks) {
				final String finalBasePath = basePath;
				sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						String filePath = finalBasePath + "/" + stock + ".dat";
						try {
							int length = FileUtil.getFileLength(filePath);//文件长度
							int position = length - TradeSignal.LENGTH;//读取位置，初始为最后一条记录
							while (true) {
								TradeSignal ts = FileUtil.readOneTradeSignal(filePath, position);
								if (ts != null && !"H".equals(ts.getDirect())) {
									totalList.add(ts);
									break;
								}

								position = position - 25;
								if (position < 0) {
									break;
								}
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
			}
			sync.sync();

			return totalList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取每只股票最后一个非H信号
	 *
	 * @param strategyId
	 * @param stocks
	 * @return
	 */
	@Override
	public Map<String, TradeSignal> getLastTradeSignalMap(int strategyId, String... stocks) {
		List<TradeSignal> list = getLastTradeSignalList(strategyId, stocks);
		Map<String, TradeSignal> map = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (TradeSignal tradeSignal : list) {
				if (tradeSignal == null) {
					continue;
				}
				map.put(tradeSignal.getStockcode(), tradeSignal);
			}
		}
		return map;
	}

	/**
	 * 取沪深300近3年数据
	 *
	 * @return
	 */
	public List<KLine> getHS300(int startDate, int endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		List<KLine> kLineList = new ArrayList<>();
		try {
			String start = sdf.format(yyyyMMdd.parse(String.valueOf(startDate)));
			List<KLine> nDaysKLineList = QuoteClient.getNDaysKLineList("10000300", start, 0, 999999, 1);
			for (KLine kLine : nDaysKLineList) {
				if (kLine.getEndTime() >= startDate && kLine.getEndTime() <= endDate) {
					kLineList.add(kLine);
				}
			}
			return kLineList;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化沪深300曲线
	 *
	 * @return
	 */
	@Override
	public List<ProfitCurve> initHS300Curve(int startDate, int endDate) {
		List<ProfitCurve> profitCurveList = new ArrayList<>();


		List<KLine> kLines = getHS300(startDate, endDate);
		KLine kLine = kLines.get(0);
		//初始原点
		ProfitCurve startPoint = new ProfitCurve();
		startPoint.setDate(String.valueOf(kLine.getEndTime()));
		startPoint.setHsProfitRate(0);
		startPoint.setProfitRate(0);
		profitCurveList.add(startPoint);

		float prePrice = kLine.getCurPx();
		for (int i = 1; i < kLines.size(); i++) {
			KLine line = kLines.get(i);
			ProfitCurve profitCurve = new ProfitCurve();
			profitCurve.setDate(String.valueOf(line.getEndTime()));
			BigDecimal b = new BigDecimal(line.getCurPx() / prePrice - 1);
			double hsProfitRate = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
			profitCurve.setHsProfitRate(hsProfitRate);
			profitCurveList.add(profitCurve);
		}

		return profitCurveList;
	}

	@Override
	public SignalIndex dataForIndex(final int strategyId, int userId, String originStocks, final int startDate, final
    int minDate, final int endDate) {
		//计算沪深300曲线
		final List<ProfitCurve> profitCurveList = initHS300Curve(startDate, endDate);

		List<ProfitCurve> oneProfitCurveList = initHS300Curve(minDate, endDate);

		long start = System.currentTimeMillis();
		SignalIndex signalIndex = new SignalIndex();
		signalIndex.setStrategyId(strategyId);

		String stocks = originStocks;

		String[] stocksArr = stocks.split(",");

		List<ResPonse<StockNetData>> resList = Collections.synchronizedList(new ArrayList<ResPonse<StockNetData>>());
		List<StockNetData> totalList = new ArrayList<>();
		List<StrategyTradeRecord> tradeRecords = new ArrayList<>();
		List<StrategyTradeRecord> resultTradeRecords = new ArrayList<>();

		SyncCall sync = new SyncCall();
		try {
			for (final String stock : stocksArr) {
				ResPonse<StockNetData> result = sync.call(new ISyncHandler() {
					@Override
					public Object syncHandler() {
						return getNetData(strategyId, stock, startDate, minDate, endDate);
					}
				});
				resList.add(result);
			}
			sync.sync();

			//需要处理异常信号
			for (int i = 0; i < resList.size(); i++) {
				if (resList.get(i).getT() != null)
					totalList.add(resList.get(i).getT());
			}
			logger.debug("dataForIndex method read cost time：" + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();


			List<SumProfit> sumProfits = new ArrayList<>();//每只股票的总收益率
			Map<String, String> nameMap = getStockNames(stocks);//查询股票名称
			for (String code : originStocks.split(",")) {
				SumProfit sumProfit = new SumProfit();
				sumProfit.setStockCode(code);
				sumProfit.setStockName(nameMap.get(code));
				sumProfit.setStrategyId(strategyId);
				sumProfit.setProfitRate("-10000");
				sumProfits.add(sumProfit);
			}

			double allSumProfit = 0;        //组合近三年总收益率
			double oneAllSumProfit = 0;     //组合近一年总收益率
			double hsMid = 0.0;
			double hsSumProfit = profitCurveList.get(profitCurveList.size() - 1).getHsProfitRate();
			//沪深300近三年总收益率
			double oneHsSumProfit = oneProfitCurveList.get(oneProfitCurveList.size() - 1).getHsProfitRate();
			//沪深300近一年总收益率


			double[] last_net = new double[totalList.size()];
			for (int i = 0; i < last_net.length; i++) {
				last_net[i] = 1.0;
			}
			for (int i = 0; i < profitCurveList.size(); i++) {
				ProfitCurve profitCurve = profitCurveList.get(i);
				String date = profitCurve.getDate();
//                if(Integer.parseInt(date)==minDate){
//                    hsMid=profitCurve.getHsProfitRate();
//                }
				double temp_netValue = 0.0;
				for (int j = 0; j < totalList.size(); j++) {
					StockNetData stockNetData = totalList.get(j);
					Map<String, Double> dayNetMap = stockNetData.getDayNet();
					Double netValue = dayNetMap.get(date);

					if (netValue == null) {
						temp_netValue += last_net[j];
					} else {
						temp_netValue += netValue;
						last_net[j] = netValue;
					}
				}
				//TODO
				BigDecimal b = new BigDecimal((temp_netValue - totalList.size()) / totalList.size());
				double profit = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				profitCurve.setProfitRate(profit);
			}

			//沪深300一年总收益计算
//            oneHsSumProfit=(hsSumProfit-hsMid)/hsMid;

			for (int i = 0; i < totalList.size(); i++) {
				StockNetData stockNetData = totalList.get(i);
				BigDecimal b = new BigDecimal(stockNetData.getTotalNet() * 100);

				for (SumProfit sp : sumProfits) {
					if (sp.getStockCode().equals(stockNetData.getStockCode())) {
						sp.setProfitRate(b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
					}
				}
				allSumProfit += stockNetData.getTotalNet();
				oneAllSumProfit += stockNetData.getMidNet();

				tradeRecords.addAll(stockNetData.getTradeRecords());
			}

			Collections.sort(sumProfits);

			for (SumProfit sp : sumProfits) {
				if ("-10000".equals(sp.getProfitRate())) {
					sp.setProfitRate("-");
				}
			}

			logger.debug("初次处理时间：" + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();

			//排序
			Collections.sort(profitCurveList);

			ExcelUtil.writeExcel(profitCurveList);

			setNewList(profitCurveList);

			//计算最高，最低点value
			List<Double> stockProfitCurves = new ArrayList<>();//股票收益曲线
			List<Double> hs300Curves = new ArrayList<>();//沪深300收益曲线
			for (ProfitCurve profitCurve : profitCurveList) {
				stockProfitCurves.add(profitCurve.getProfitRate());
				hs300Curves.add(profitCurve.getHsProfitRate());
			}
			double maxValueStock = Collections.max(stockProfitCurves);
			double minValueStock = Collections.min(stockProfitCurves);

			double maxValue300 = Collections.max(hs300Curves);
			double minValue300 = Collections.min(hs300Curves);

			if (maxValueStock < maxValue300) {
				maxValueStock = maxValue300;
			}
			if (minValueStock > minValue300) {
				minValueStock = minValue300;
			}
			signalIndex.setHighValue(maxValueStock);
			signalIndex.setLowValue(minValueStock);

			//设置盈利曲线
			signalIndex.setProfitCurves(profitCurveList);

			signalIndex.setSumProfits(sumProfits);
			allSumProfit = allSumProfit / totalList.size();
			oneAllSumProfit = oneAllSumProfit / totalList.size();
			logger.debug("组合近三年收益率{}，沪深300近三年收益率{}，组合近一年收益率{},沪深300近一年收益率{}", allSumProfit, hsSumProfit,
                    oneAllSumProfit, oneHsSumProfit);
			signalIndex.setAllSumProfit(new BigDecimal(allSumProfit * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "%");
			signalIndex.setAllComHS(new BigDecimal((allSumProfit - hsSumProfit) * 100).setScale(2, BigDecimal
                    .ROUND_HALF_UP).toPlainString() + "%");
			signalIndex.setOneAllSumProfit(new BigDecimal(oneAllSumProfit * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "%");
			signalIndex.setOneAllComHs(new BigDecimal((oneAllSumProfit - oneHsSumProfit) * 100).setScale(2, BigDecimal
                    .ROUND_HALF_UP).toPlainString() + "%");

			logger.debug("处理完成时间：" + (System.currentTimeMillis() - start));

			// List<StrategyTradeRecord> resultTradeRecords = this.queryPageStrategyTradeRecord(strategyId, userId, 1,
			// 3, originStocks.split(","));
			Collections.sort(tradeRecords);

			if (tradeRecords != null && tradeRecords.size() > 0) {
				for (int i = tradeRecords.size() - 1; i >= 0; i--) {
					StrategyTradeRecord str = tradeRecords.get(i);
					if (!"H".equals(str.getDirection())) {
						str.setStockName(nameMap.get(str.getStockCode()));
						resultTradeRecords.add(str);
					}
					if (resultTradeRecords.size() == 3) {
						break;
					}
				}
			}
			signalIndex.setStrategyTradeRecords(resultTradeRecords);
			return signalIndex;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询上一条购买记录
	 *
	 * @param filePath
	 * @param sellTime 卖出时间
	 * @return
	 */
	private TradeSignal getLastTimeBuyTradeSignal(String filePath, long sellTime) {
		TradeSignal buySignal = null;
		try {
			List<TradeSignal> list = FileUtil.read(filePath, 0, -1);
			if (list == null && list.size() == 0) {
				return buySignal;
			}
			for (TradeSignal tradeSignal : list) {
				if (tradeSignal.getDirect().equals("B") && tradeSignal.getDatetime() < sellTime) {
					buySignal = tradeSignal;
				} else if (tradeSignal.getDatetime() > sellTime) {
					break;
				}
			}
			return buySignal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buySignal;
	}

	/**
	 * 从list集合重获取获取200点
	 *
	 * @param list
	 * @return
	 */
	private void setNewList(List<ProfitCurve> list) {
		//大于200 截取
		if (list.size() > 200) {
			List newResult = new ArrayList();
			ProfitCurve one = list.get(0); //开头的点
			ProfitCurve end = list.get(list.size() - 1);//结束的点

			list.remove(0);
			list.remove(list.size() - 1);

			int size = list.size();
			double n = size / 200.0; //平均取点数
			double sum = 0; //总取点数
			int int_sum = 0;//总取点数 int值
			int last_int_sum = 0;// 上一次取点数 int值
			int get_n;//当前取几个点

			while (true) {
				sum += n;
				last_int_sum = int_sum;
				int_sum = (int) sum;
				get_n = int_sum - last_int_sum;

				if (get_n >= n) {
					int temp_result = last_int_sum + (int) (Math.random() * ((int_sum - 1 - last_int_sum) + 1));
					newResult.add(list.get(temp_result));
				} else {
					newResult.add(list.get(last_int_sum));
				}


				if (newResult.size() == 198) break;
			}

			newResult.add(0, one);
			newResult.add(end);
			list.clear();
			list.addAll(newResult);
		}
	}


	/**
	 * 获取净值数据
	 *
	 * @param strategyId
	 * @param stock
	 * @param startDate
	 * @param minDate
	 * @param endDate
	 * @return
	 */
	@Override
	public StockNetData getNetData(int strategyId, String stock, int startDate, int minDate, int endDate) {

		String filePath = getStockPath(strategyId, stock);
		List<TradeSignal> list = null;
		try {
			list = FileUtil.read(filePath, 0, -1);
			if (list == null) {
				return null;
			}

			StockNetData stockNetData = new StockNetData();

			double threeNet = 1;    //近3年累计收益，初始为1
			double oneNet = 1;      //近1年累计收益，初始为1

			boolean oneBuy = false;
			boolean threeBuy = false;
			LinkedHashMap<String, Double> averageNetMap = new LinkedHashMap<>();
			List<StrategyTradeRecord> strategyTradeRecordList = new ArrayList<>();
			double lastClosePrice = 0.00;
			double buyPrice = 0.00;

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TradeSignal ts = list.get(i);
					long _date = ts.getDatetime() / 10000;
					if (_date < startDate) continue;
					if (_date > endDate) break;
					String date = String.valueOf(_date);
					String direct = ts.getDirect();//买卖方向

					double profit = 0;

					//如果第一个条件不是买入 继续循环直到出现第一个买入信号
					if (!threeBuy && !direct.equals("B")) {
						continue;
					}

					if (direct.equals("B")) {
						threeBuy = true;
						threeNet += threeNet * ((ts.getCloseprice() - ts.getPrice()) / new Double(ts.getPrice()));
						if (_date > minDate) {
							oneBuy = true;
							oneNet += oneNet * ((ts.getCloseprice() - ts.getPrice()) / new Double(ts.getPrice()));
						}
						buyPrice = ts.getPrice();
						profit = 0;

					} else if (direct.equals("H")) {
						threeNet += threeNet * ((ts.getCloseprice() - lastClosePrice) / new Double(lastClosePrice));
						if (oneBuy && _date > minDate) {
							oneNet += oneNet * ((ts.getCloseprice() - lastClosePrice) / new Double(lastClosePrice));
						}
					} else if (direct.equals("S")) {
						threeNet += threeNet * ((ts.getPrice() - lastClosePrice) / new Double(lastClosePrice));
						if (oneBuy && _date > minDate) {
							oneNet += oneNet * ((ts.getPrice() - lastClosePrice) / new Double(lastClosePrice));
						}
						profit = (ts.getPrice() - buyPrice) / buyPrice;
					}
					BigDecimal df = new BigDecimal(threeNet);
					df = df.setScale(4, BigDecimal.ROUND_HALF_UP);
					threeNet = df.doubleValue();
					df = new BigDecimal(oneNet);
					df = df.setScale(4, BigDecimal.ROUND_HALF_UP);
					oneNet = df.doubleValue();

					double sum = averageNetMap.containsKey(date) ? averageNetMap.get(date) : 0;
					averageNetMap.put(date, sum + threeNet);

					if (!direct.equals("H")) {
						StrategyTradeRecord strategyTradeRecord = new StrategyTradeRecord();
						strategyTradeRecord.setDate(date);
						df = new BigDecimal(profit * 100);
						df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
						strategyTradeRecord.setProfitRate(df.toPlainString());
						strategyTradeRecord.setDirection(direct);
						strategyTradeRecord.setStockCode(stock);
						df = new BigDecimal(new Double(ts.getPrice()) / 10000);
						df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
						strategyTradeRecord.setPrice(df.toPlainString());
						strategyTradeRecordList.add(strategyTradeRecord);
					}
					lastClosePrice = ts.getCloseprice();//初始化收盘价
				}

			}

			List<StrategyTradeRecord> resultList = new ArrayList<>();
			if (strategyTradeRecordList.size() > 3) {
				for (int j = strategyTradeRecordList.size() - 1; j >= strategyTradeRecordList.size() - 3; j--) {
					resultList.add(strategyTradeRecordList.get(j));
				}
			}

			BigDecimal df = new BigDecimal(String.valueOf(oneNet - 1));
			df = df.setScale(4, BigDecimal.ROUND_HALF_UP);
			stockNetData.setMidNet(df.doubleValue());
			df = new BigDecimal(String.valueOf(threeNet - 1));
			df = df.setScale(4, BigDecimal.ROUND_HALF_UP);

			stockNetData.setTotalNet(df.doubleValue());
			stockNetData.setStockCode(stock);
			stockNetData.setDayNet(averageNetMap);
			stockNetData.setTradeRecords(resultList);

			return stockNetData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, String> getStockNames(String stockCodes) {
		Map<String, String> stockMap = new HashMap<>();

		List<String> codes = Arrays.asList(stockCodes.split(","));
		Map<String,StockInfo> stockInfoMap = QuoteClient.queryStockInfoByCodes(codes);


		if (stockInfoMap != null && stockInfoMap.size() > 0) {
			for (Map.Entry<String, StockInfo> entry : stockInfoMap.entrySet()) {
				StockInfo stockInfo = entry.getValue();
				if(null == stockInfo){
					continue;
				}
				stockMap.put(stockInfo.getCode(), stockInfo.getName());
			}
		}
		return stockMap;
	}

	@Override
	public String getStockName(String stockCode) {
		Map<String, String> stockMap = getStockNames(stockCode);
		if (stockMap != null) {
			return stockMap.get(stockCode);
		}
		return null;
	}

	public String getStockPath(int strategyId, String stock) {
		String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
		basePath += "/" + strategyId;
		String filePath = basePath + "/" + stock + ".dat";
		return filePath;
	}

	/**
	 * 计算收益率，石磊
	 *
	 * @param strategyId
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @param stocks
	 * @return
	 */
	@Override
	public List<SumProfit> querySumProfitNew(int strategyId, long startDate, long endDate, int type, String...
            stocks) {

		List<SumProfit> result = new ArrayList<>();
		Map<String, Double> netMap = new ConcurrentHashMap<>();
		Map<String, List<TradeSignal>> map = this.loadTradeSignal(strategyId, startDate, endDate, stocks);

		for (String key : map.keySet()) {
			List<TradeSignal> tradeSignalList = map.get(key);
			Collections.sort(tradeSignalList);
			Collections.reverse(tradeSignalList);

			double net = 1;//净值初始为1
			int lastClosePrice = 0;

			for (int i = 0; i < tradeSignalList.size(); i++) {
				TradeSignal ts = tradeSignalList.get(i);
				String direct = ts.getDirect();
				double profit = 0.00;
				if ("B".equals(direct)) {
					profit = net * ((ts.getCloseprice() - ts.getPrice()) / new Double(ts.getPrice()));
					lastClosePrice = ts.getCloseprice();
				} else if ("H".equals(direct) && lastClosePrice > 0) {
					profit = net * ((ts.getCloseprice() - lastClosePrice) / new Double(lastClosePrice));
					lastClosePrice = ts.getCloseprice();
				} else if ("S".equals(direct) && lastClosePrice > 0) {
					profit = net * ((ts.getPrice() - lastClosePrice) / new Double(lastClosePrice));
					lastClosePrice = 0;
				}
				BigDecimal b = new BigDecimal(profit);
				profit = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				net = net + profit;
//                net = net + profit;
			}

			netMap.put(key, net - 1);
		}

		for (String key : netMap.keySet()) {
			SumProfit sumProfit = new SumProfit();
			sumProfit.setStockCode(key);
			sumProfit.setStrategyId(strategyId);
			BigDecimal b = new BigDecimal(netMap.get(key));
			sumProfit.setProfitRate(b.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString());
			result.add(sumProfit);
		}
		return result;
	}

	@Override
	public JSONObject deleteSignalFile(int strategyId) {
		String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
		basePath += "/" + strategyId;
		int num = FileUtil.deleteFile(basePath);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "0000");
		jsonObject.put("strategyId", strategyId);
		jsonObject.put("fileNum", num);
		return jsonObject;
	}

	/**
	 * @param date yyyyMMddHHmi
	 * @return
	 */
	private int getDateFromTs(long date) {
		return (int) (date / 10000);
	}

	private List<TradeSignal> getCountStockList(int strategyId, List<String> stockFileNames, long time) {
		List<TradeSignal> stockList = new ArrayList<>();

		//遍历所有股票名称 过滤出当天有过交易的股票
		for (String stockCode : stockFileNames) {
			TradeSignal ts = getLastTradeSignal(strategyId, stockCode);
			if (ts == null) {
				continue;
			}

			//判断是否为time的交易数据
			if (time != getDateFromTs(ts.getDatetime())) {
				continue;
			}

			//记录下来股票代码
			stockList.add(ts);
		}
		return stockList;
	}

	/**
	 * 查询某策略下当前所有持仓股票
	 *
	 * @param strategyId
	 * @param stockFileNames
	 * @return
	 */
	private List<TradeSignal> getCountStockList(int strategyId, List<String> stockFileNames) {
		List<TradeSignal> stockList = new ArrayList<>();

		//遍历所有股票名称 过滤出当天有过交易的股票
		for (String stockCode : stockFileNames) {
			TradeSignal ts = getLastTradeSignal(strategyId, stockCode);
			if (ts == null) {
				continue;
			}

			//如果最后一笔是卖出，则不是当前持仓
			if ("S".equals(ts.getDirect())) {
				continue;
			}

			ts = getLastBuyTradeSignal2(strategyId, stockCode);
			//记录下来股票代码
			stockList.add(ts);
		}
		return stockList;
	}

	/**
	 * 查询当前持有的股票
	 *
	 * @param strategyId
	 * @param stockFileNames
	 * @return
	 */
	private List<String> findMyHoldStock(int strategyId, List<String> stockFileNames) {
		List<String> stockList = new ArrayList<>();

		//遍历所有股票名称 过滤出当天有过交易的股票
		for (String stockCode : stockFileNames) {
			TradeSignal ts = getLastTradeSignal(strategyId, stockCode);
			if (ts == null) {
				continue;
			}

			//如果最后一笔是卖出，则不是当前持仓
			if ("S".equals(ts.getDirect())) {
				continue;
			}

			//记录下来股票代码
			stockList.add(ts.getStockcode());
		}
		return stockList;
	}

	/**
	 * 只支持统计date为当前最后一个交易日
	 *
	 * @param strategyId
	 * @param date
	 * @return
	 */
	@Override
	public String countProfit(int strategyId, Date date) {
		try {
			JSONObject erroMsg = new JSONObject();

			//处理时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long stat_Date = Long.valueOf(sdf.format(date));

			Strategy strategy = strategyService.getStrategy(strategyId);
			if (strategy == null) {
				erroMsg.put("code", "2");
				erroMsg.put("message", "不存在该策略");
				return erroMsg.toJSONString();
			}

			//获取策略ID下的所有股票文件
			List<String> stockFileNames = loadStockFileName(strategyId);
			if (stockFileNames == null || stockFileNames.size() == 0) {
				erroMsg.put("code", "2");
				erroMsg.put("message", "策略下股票文件为空");
				return erroMsg.toJSONString();
			}

			ProfitStat ps = new ProfitStat();
			ps.setName(strategy.getName());
			ps.setStrategyId(strategyId);

			//过滤出当天有过交易的股票
			List<TradeSignal> stockList = getCountStockList(strategyId, stockFileNames, stat_Date);

			for (TradeSignal ts : stockList) {
				double profilt = 0.0;

				if ("B".equals(ts.getDirect())) {  //当日买入
					int closePrice = ts.getCloseprice();
					if (closePrice == 0) {
						closePrice = (int) (QuoteUtil.getCurPrice(ts.getStockcode()) * 10000);
					}
					profilt = ((double) (closePrice - ts.getPrice())) / ts.getPrice();
				} else if ("H".equals(ts.getDirect())) {//当日持有
					int closePrice = ts.getCloseprice();
					if (closePrice == 0) {
						closePrice = (int) (QuoteUtil.getCurPrice(ts.getStockcode()) * 10000);
					}

					TradeSignal lastBuy = getLastBuyTradeSignal(strategyId, ts.getStockcode());
					profilt = ((double) (closePrice - lastBuy.getPrice())) / lastBuy.getPrice();
					ts = lastBuy;
				} else if ("S".equals(ts.getDirect())) {//当日卖出
					TradeSignal lastBuy = getLastBuyTradeSignal(strategyId, ts.getStockcode());
					profilt = ((double) (ts.getPrice() - lastBuy.getPrice())) / lastBuy.getPrice();
				}

				ps.countProfilt(getDateFromTs(ts.getDatetime()), profilt, ts.getDirect(), ts.getStockcode());
			}

			return ps.toJson();

		} catch (Exception e) {
			logger.error(e);

		}
		return "2;策略没有可用数据";
	}

	@Override
	public List<Stock5dayAndNowHold> countfiveDay(int strategyId, double five) {
		Strategy strategy = strategyService.getStrategy(strategyId);
		if (strategy == null) {
			return null;
		}

		//查询当日买入
		List<Stock5dayAndNowHold> resList = new ArrayList<>();
		List<StockTradeToday> listNow = stockTradeTodaySerive.queryTodayTrade(strategyId, StockTradeToday.TYPE_BUY, 0,
				1000);
		Stock5dayAndNowHold s5;
		if (listNow != null && listNow.size() > 0) {
			for (StockTradeToday stt : listNow) {
				s5 = disData(stt.getStockCode(), stt.getPrice(), stt.getTradeTime(), five, strategy.getName());
				if (s5 == null) {
					continue;
				}
				resList.add(s5);
			}
		}

		// 查询近5日买入
		List<StockTradeNear5days> list5 = stockTradeNear5daysService.queryData(strategyId, 0, 1000);
		if (list5 != null && list5.size() > 0) {
			for (StockTradeNear5days temp : list5) {
				s5 = disData(temp.getStockCode(), temp.getPrice(), temp.getTradeTime(), five, strategy.getName());
				if (s5 == null) {
					continue;
				}
				resList.add(s5);
			}
		}
		//排序
		Collections.sort(resList, new Comparator<Stock5dayAndNowHold>() {
			@Override
			public int compare(Stock5dayAndNowHold o1, Stock5dayAndNowHold o2) {
				return o1.getProfit() >= o2.getProfit() ? -1 : 1;
			}
		});
		return resList;
	}

	private Stock5dayAndNowHold disData(String stockCode, double money, long tradeTime, double arg, String name) {
		//从行情获取到现价
		CurStatus cs = QuoteUtil.getCurStatus(stockCode);
		double curPrice = cs.getCurPrice();
		//从行情获取到昨收
		double closePrice = cs.getClosePrice();
		//处理买入价格
		double price = divideDouble(money, 10000.00, 2);
		//计算当日收益
		double profit = divideDouble(subtract(curPrice, closePrice, 2), closePrice, 4);
		//如果当日收益小于arg的话，不计入
		if (profit < arg) {
			return null;
		}
		//计算累计收益
		double countProfit = divideDouble(subtract(curPrice, price, 2), price, 4);
		Stock5dayAndNowHold s5 = new Stock5dayAndNowHold();
		s5.setStrategyName(name);
		s5.setStockCode(stockCode);
		s5.setStockName(cs.getName());
		s5.setTradeTime(tradeTime);
		s5.setProfit(profit);
		s5.setCountProfit(countProfit);
		return s5;
	}

	@Override
	public List<Stock5dayAndNowHold> countNowHoldStock(int strategyId, double now) {
		Strategy strategy = strategyService.getStrategy(strategyId);
		if (strategy == null) {
			return null;
		}

		List<Stock5dayAndNowHold> resList = new ArrayList<>();
		Stock5dayAndNowHold s5;

		//查询出当前还持仓的股票
		List<String> stockFileNames = getAllHoldStock(strategyId);
		List<TradeSignal> listTs = getCountStockList(strategyId, stockFileNames);
		if (listTs != null && listTs.size() > 0) {
			for (TradeSignal ts : listTs) {
				if (ts == null || ts.getStockcode() == null) {
					continue;
				}
				s5 = disData(ts.getStockcode(), ts.getPrice(), ts.getDatetime(), now, strategy.getName());
				if (s5 == null) {
					continue;
				}
				resList.add(s5);
			}
		}
		//排序
		Collections.sort(resList, new Comparator<Stock5dayAndNowHold>() {
			@Override
			public int compare(Stock5dayAndNowHold o1, Stock5dayAndNowHold o2) {
				return o1.getProfit() >= o2.getProfit() ? -1 : 1;
			}
		});
		return resList;
	}

	/**
	 * 过滤出符合条件的涨停股票
	 *
	 * @param list
	 * @return
	 */
	private List<String> getZtStockList(List<CurStatus> list) {
		List<String> listS = new ArrayList<>();
		float price = 0;
		for (CurStatus cs : list) {
			price = multiply(cs.getClosePrice(), 1.1, 2);
			if (price > cs.getCurPrice()) {
				continue;
			}
			listS.add(cs.getCode());
		}
		return listS;
	}

	@Override
	public int disReportData(int strategyId) {
		//查询当日股票涨跌幅情况
		List<CurStatus> list = QuoteClient.getStockByNetchange();
		if (list == null || list.size() == 0) {
			return 0;
		}
		//获取涨停股票代码
		List<String> listS = getZtStockList(list);
		if (listS.size() == 0) {
			return 0;
		}

		//当前机器人所有股票文件
		List<String> stockFileNames;
		//当前机器人持有的股票
		List<String> listStocks;
		List<StrategyLimitUp> listUp = new ArrayList<>();
		StrategyLimitUp up;
		stockFileNames = getAllHoldStock(strategyId);
		//查询出当前还持仓的股票
		listStocks = findMyHoldStock(strategyId, stockFileNames);
		if (listStocks != null && listStocks.size() > 0) {
			for (String sto : listStocks) {
				if (listS.contains(sto)) {
					up = new StrategyLimitUp();
					up.setStrategyId(strategyId);
					up.setStockCode(sto);
					up.setCreateTime(new Date());
					listUp.add(up);
				}
			}
		}
		//因该没那么多涨停的股票，超不过5000就一次性批量插入
		return strategyLimitUpService.batchList(listUp);
	}


	private  CurStatus getCurStatus(String stockCode) {
		CurStatus curStatus = QuoteClient.getCurStatusByCode(stockCode);
		if(curStatus.getCurPrice()<=0){
			curStatus.setCurPrice(curStatus.getClosePrice());
		}

		return curStatus;
	}
	@Override
	public DnaStockHold findDnaStockHold(String stockCode, int userId) {
		CurStatus curStatus = getCurStatus(stockCode);//获取股票名称
		String stockName = curStatus==null?"":curStatus.getName();
		List<DnaStockHold> holdList = new ArrayList<>();
		Map<Integer, StrategyUser> myStrategyMap = strategyUserService.findStrategyMapByUserId(userId, 1);
		List<Strategy> strategyList = strategyService.findAll();
		DnaStockHold dnaStockHold = new DnaStockHold();
		dnaStockHold.setStockCode(stockCode);
		dnaStockHold.setStockName(stockName);
		dnaStockHold.setDirect("S");

		//计算机器人持有的标的股票收益率
		if(null != strategyList && strategyList.size()>0){
			for(Strategy strategy : strategyList){
				TradeSignal lastTradeSignal = this.getLastTradeSignal(strategy.getId(), stockCode);
				if(null != lastTradeSignal){
					if(!"S".equals(lastTradeSignal.getDirect())){
						TradeSignal buyTradeSignal = null;
						if("B".equals(lastTradeSignal.getDirect())){
							buyTradeSignal = lastTradeSignal;
						} else {
							buyTradeSignal = this.getLastBuyTradeSignal(strategy.getId(), stockCode);
						}
						DnaStockHold stockHold = new DnaStockHold();
						stockHold.setStrategyId(strategy.getId());
						stockHold.setStockCode(stockCode);
						stockHold.setStockName(stockName);
						String tradetime = buyTradeSignal.getDatetime()/10000+"";
						StringBuilder sb = new StringBuilder();
						sb.append(tradetime.substring(0,4)).append(".").append(tradetime.substring(4,6)).append(".").append(tradetime.substring(6));
						stockHold.setTradeTime(sb.toString());
						stockHold.setDirect(lastTradeSignal.getDirect());
						double profit = (curStatus.getCurPrice()*TradeSignal.PRICE_FACTOR-buyTradeSignal.getPrice())/buyTradeSignal.getPrice();
						BigDecimal b = new BigDecimal(profit);
						double f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
						stockHold.setProfit(f1);
						holdList.add(stockHold);
					}

				}
			}
		}

		boolean negative = isNegative(holdList);//是否机器人收益全部为负收益
		int maxOneYearProfitSid = maxOneYearProfitStrategy(strategyList);//取近一年收益最高的策略
		int randomSid = randomStrategy(myStrategyMap);//从已购买的策略里随机取一个
//		int maxProfitSid = maxProfitSid(myStrategyMap, holdList);//从已购买策略里取收益最高的策略
		int displaySid = 0;
		//机器人持有
		if(holdList.size()>0){
			if(!negative){//有正收益 -- 获取收益最大的机器人
				displaySid = this.getMaxPro(holdList);
				this.setDnaStockHold(displaySid, dnaStockHold, holdList);
			} else {//全是负收益
				if(myStrategyMap.size()>0){ //用户购买机器人 -- 随机展示一个
					displaySid = randomSid;
					this.setDnaStockHold(displaySid, dnaStockHold, holdList);
				} else {//否则 展示一个近一年收益最高的机器人
					displaySid = maxOneYearProfitSid;
					this.setDnaStockHold(displaySid, dnaStockHold, holdList);
					//无持仓
				}
			}

		} else {//机器人全都不持有
			if(myStrategyMap.size()>0){//如果购买机器人 随便选择一个
				displaySid = randomSid;
				//无持仓
			} else {//否则 展示一个近一年收益最高的机器人
				displaySid = maxOneYearProfitSid;
				//无持仓
			}
		}

		if(displaySid==0){
			displaySid = maxOneYearProfitSid;
		}

		//文案显示处理
		StrategyUser strategyUser = myStrategyMap.get(displaySid);
		Strategy strategy = strategyService.getStrategy(displaySid);
		dnaStockHold.setStrategyId(displaySid);
		dnaStockHold.setStrategyName(strategy.getName());
		dnaStockHold.setLogo(strategy.getLogo());

		if(null != strategyUser) {
			String describe = "主人我们又见面啦，我是"+strategy.getName()+"。我帮你找到了与"+stockName+"当前走势最相似的历史走势，并进行了统计分析。";
			dnaStockHold.setDescribe(describe);
			dnaStockHold.setBuy(true);
		} else {
			String describe = "我是"+strategy.getName()+"，是会做股票模拟交易的人工智能。我帮你找到了与"+stockName+"当前走势最相似的历史走势，并进行了统计分析。感受科技的力量吧";
			dnaStockHold.setDescribe(describe);
			dnaStockHold.setBuy(false);
		}

		return dnaStockHold;
	}

	private void setDnaStockHold(int displaySid, DnaStockHold dnaStockHold, List<DnaStockHold> list){
		for(DnaStockHold hold : list){
			if(hold.getStrategyId() == displaySid){
				dnaStockHold.setStockCode(hold.getStockCode());
				dnaStockHold.setStockName(getStockName(hold.getStockCode()));
				dnaStockHold.setProfit(hold.getProfit());
				dnaStockHold.setTradeTime(hold.getTradeTime());
				dnaStockHold.setDirect(hold.getDirect());
			}
		}
	}

	/**
	 * 是否机器人收益全部为负收益
	 * @param holdList
	 * @return
	 */
	private boolean isNegative(List<DnaStockHold> holdList){
		if(null!=holdList && holdList.size()>0){
			for(DnaStockHold stockHold : holdList){
				if(stockHold.getProfit()>0){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 获取收益最大的策略
	 * @param holdList
	 * @return
	 */
	private int getMaxPro(List<DnaStockHold> holdList){
		int strategyId = -1;
		double profit = Double.MIN_VALUE;

		if(null!=holdList && holdList.size()>0){
			for(DnaStockHold stockHold : holdList){
				if(stockHold.getProfit()>profit){
					profit = stockHold.getProfit();
					strategyId = stockHold.getStrategyId();
				}
			}
		}
		return strategyId;
	}

	/**
	 * 取近一年收益最高的策略
	 * @return 策略ID
	 */
	private int maxOneYearProfitStrategy(List<Strategy> strategyList){
		StrategyStockOneProfit result = new StrategyStockOneProfit();
		result.setProfit(Double.MIN_VALUE);
		if(null!=strategyList && strategyList.size()>0){
			for(Strategy strategy : strategyList){
				StrategyStockOneProfit oneProfit = strategyStockOneProfitService.findStrategyStockOneProfit(strategy.getId(), "99999999");
				if(oneProfit.getProfit()>result.getProfit()){
					result = oneProfit;
				}
			}
		}

		return result.getStrategyId();
	}

	/**
	 * 从已购买的策略中随机取一个
	 * @param myStrategyMap
	 * @return 策略ID
	 */
	private int randomStrategy(Map<Integer, StrategyUser> myStrategyMap) {
		if(null != myStrategyMap && myStrategyMap.size()>0){
			Integer[] keys = myStrategyMap.keySet().toArray(new Integer[0]);
			Random random = new Random();
			Integer randomKey = keys[random.nextInt(keys.length)];
			StrategyUser strategyUser = myStrategyMap.get(randomKey);
			return strategyUser.getStrategyId();
		}
		return 0;
	}

	/**
	 * 从已购买策略里取收益最高的策略
	 * @param myStrategyMap
	 * @param holdList
	 * @return
	 */
	private int maxProfitSid(Map<Integer, StrategyUser> myStrategyMap, List<DnaStockHold> holdList){
		if(null != myStrategyMap && myStrategyMap.size()>0){
			double profit = -100000d;
			int sid = 0;
			if(null!=holdList && holdList.size()>0){
				List<DnaStockHold> temp = new ArrayList<>();//已购买并且有持仓的策略
				for(DnaStockHold stockHold : holdList){
					if(myStrategyMap.containsKey(stockHold.getStrategyId())){
						temp.add(stockHold);
					}
				}

				for(DnaStockHold stock : temp){
					if(stock.getProfit()>profit){
						profit = stock.getProfit();
						sid = stock.getStrategyId();
					}
				}
			}
			return sid;
		}
		return 0;
	}


	private List<String> getAllHoldStock(int strategyId) {
		//获取策略ID下的所有股票文件
		List<String> stockFileNames = loadStockFileName(strategyId);
		return stockFileNames;
	}

	private String formatPrice(double price) {
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setMaximumFractionDigits(2);
		return fmt.format(price);
	}

	/**
	 * 整理返回的数据
	 *
	 * @param dataMap
	 * @param daySellMap
	 * @param allCountMap
	 * @return
	 */
	private String disJsonStr(Map<Long, JSONObject> dataMap, Map<String, JSONObject> daySellMap, Map<String,
            JSONObject> allCountMap) {
		try {
			JSONObject res = new JSONObject();
			double upProfit;
			double downProfit;
			int upNum;
			int downNum;
			JSONObject upJson;
			JSONObject downJson;

			//五个交易日明细
			JSONArray ja = new JSONArray();
			JSONObject temp;
			//处理5个交易日的买入数据明细
			Set<Long> set = dataMap.keySet();
			Iterator<Long> it = set.iterator();
			long dataTime;
			while (it.hasNext()) {
				dataTime = it.next();
				temp = dataMap.get(dataTime);
				temp.put("time", dataTime);
				ja.add(temp);
			}

			res.put("detailList", ja);

			//处理当日卖出数据
			if (daySellMap.size() > 0) {
				if (daySellMap.get("up") != null) {
					upJson = daySellMap.get("up");
					upProfit = upJson.getDoubleValue("profit");
					upNum = upJson.getIntValue("num");
					res.put("daySellUpNum", upNum);
					res.put("daySellUpProfit", divideDouble(upProfit, upNum, 2));
				}
				if (daySellMap.get("down") != null) {
					downJson = daySellMap.get("down");
					downProfit = downJson.getDoubleValue("profit");
					downNum = downJson.getIntValue("num");
					res.put("daySellDownNum", downNum);
					res.put("daySellDownProfit", divideDouble(downProfit, downNum, 2));
				}
			}

			//处理总盈利情况数据
			upJson = allCountMap.get("up");
			downJson = allCountMap.get("down");
			upProfit = upJson.getDoubleValue("profit");
			downProfit = downJson.getDoubleValue("profit");
			upNum = upJson.getIntValue("num");
			downNum = downJson.getIntValue("num");

			res.put("allCountUpNum", upNum);
			res.put("allCountDownNum", downNum);
			res.put("allCountUpProfit", divideDouble(upProfit, upNum, 2));
			res.put("allCountDownProfit", divideDouble(downProfit, downNum, 2));

			return res.toJSONString();
		} catch (Exception e) {
			logger.error("整理统计数据错误：", e);
			return "2:整理统计数据错误";
		}
	}

	/**
	 * 计算总盈亏情况和盈利率
	 *
	 * @param profit
	 * @param allCountMap
	 */
	private void judgeAllCount(double profit, Map<String, JSONObject> allCountMap) {
		JSONObject obj;
		if (profit > 0) {
			if (allCountMap.get("up") != null) {
				obj = allCountMap.get("up");
				obj.put("num", obj.getIntValue("num") + 1);
				obj.put("profit", add(obj.getDoubleValue("profit"), profit, 2));
				allCountMap.put("up", obj);
			} else {
				obj = new JSONObject();
				obj.put("num", 1);
				obj.put("profit", profit);
				allCountMap.put("up", obj);
			}
		} else {
			if (allCountMap.get("down") != null) {
				obj = allCountMap.get("down");
				obj.put("num", obj.getIntValue("num") + 1);
				obj.put("profit", add(obj.getDoubleValue("profit"), profit, 2));
				allCountMap.put("down", obj);
			} else {
				obj = new JSONObject();
				obj.put("num", 1);
				obj.put("profit", profit);
				allCountMap.put("down", obj);
			}
		}
	}


	/**
	 * 统计当日卖出的情况，将盈利和亏损保存到对应的天数下
	 *
	 * @param profit
	 * @param daySellMap
	 */
	private void judgeShell(double profit, Map<String, JSONObject> daySellMap) {
		JSONObject obj;
		if (profit > 0) {
			if (daySellMap.get("up") != null) {
				obj = daySellMap.get("up");
				obj.put("num", obj.getIntValue("num") + 1);
				obj.put("profit", add(obj.getDoubleValue("profit"), profit, 2));
				daySellMap.put("up", obj);
			} else {
				obj = new JSONObject();
				obj.put("num", 1);
				obj.put("profit", profit);
				daySellMap.put("up", obj);
			}
		} else {
			if (daySellMap.get("down") != null) {
				obj = daySellMap.get("down");
				obj.put("num", obj.getIntValue("num") + 1);
				obj.put("profit", add(obj.getDoubleValue("profit"), profit, 2));
				daySellMap.put("down", obj);
			} else {
				obj = new JSONObject();
				obj.put("num", 1);
				obj.put("profit", profit);
				daySellMap.put("down", obj);
			}
		}
	}

	/**
	 * 统计5个交易日买入的情况，将盈利和亏损保存到对应的天数下
	 *
	 * @param profit
	 * @param obj
	 * @param dataMap
	 * @param dataTime
	 */
	private void judge(double profit, JSONObject obj, Map<Long, JSONObject> dataMap, long dataTime) {
		if (profit > 0) {
			if (obj != null) {
				int up = obj.getIntValue("up");
				obj.put("up", up + 1);
			} else {
				obj = new JSONObject();
				obj.put("up", 1);
				obj.put("down", 0);
			}
		} else {
			if (obj != null) {
				int down = obj.getIntValue("down");
				obj.put("down", down + 1);
			} else {
				obj = new JSONObject();
				obj.put("down", 1);
				obj.put("up", 0);
			}
		}
		dataMap.put(dataTime, obj);
	}


	/**
	 * 小数相减,四舍五入进位
	 *
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	private double subtract(double arg1, double arg2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
		BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
		return bd1.subtract(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 小数相加,四舍五入进位
	 *
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	private double add(double arg1, double arg2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
		BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
		return bd1.add(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	private static float multiply(double arg1, double arg2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
		BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
		return bd1.multiply(bd2).setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}


	@Override
	public TradeSignal getLastTradeSignal(int strategyId, int date, String stockCode) {
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			String filePath = basePath + "/" + stockCode + ".dat";
			TradeSignal ts = FileUtil.readByTime(filePath, date);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TradeSignal getLastBuyTradeSignal(int strategyId, int date, String stockCode) {
		try {
			String basePath = QuantTradePlateConfig.getInstance().getStockFilePath();
			basePath += "/" + strategyId;
			String filePath = basePath + "/" + stockCode + ".dat";
			TradeSignal ts = FileUtil.readLastBuyByTime(filePath, date);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
