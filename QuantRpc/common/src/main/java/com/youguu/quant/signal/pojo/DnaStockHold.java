package com.youguu.quant.signal.pojo;

/**
 * Created by leo on 2017/4/14.
 */
public class DnaStockHold {
	private int strategyId;//策略ID
	private String strategyName;
	private String logo;//机器人logo，图片URL
	private String describe;//描述
	private boolean isBuy;//是否购买了该策略
	private String stockCode;//股票代码
	private String stockName;//股票名称
	private String tradeTime;//模拟买入时间
	private String direct;//股票方向 B:买入 S:卖出 H:持有
	private double profit;//累计收益率 45.12%

	public int getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DnaStockHold{");
		sb.append("strategyId=").append(strategyId);
		sb.append(", strategyName='").append(strategyName).append('\'');
		sb.append(", logo='").append(logo).append('\'');
		sb.append(", describe='").append(describe).append('\'');
		sb.append(", isBuy=").append(isBuy);
		sb.append(", stockCode='").append(stockCode).append('\'');
		sb.append(", stockName='").append(stockName).append('\'');
		sb.append(", tradeTime='").append(tradeTime).append('\'');
		sb.append(", direct='").append(direct).append('\'');
		sb.append(", profit=").append(profit);
		sb.append('}');
		return sb.toString();
	}
}
