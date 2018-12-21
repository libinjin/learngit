package com.youguu.quant.klinesim;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "klineSimResult", noClassnameStored = true)
public class KlineSimResult implements Serializable{
	
	/**
	* @Fields serialVersionUID : 
	*/
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	//股票名称
	private String name;
	//平均涨跌幅
	private double avgRation;
	//上涨比率
	private double upRate;
	//下跌比率
	private double downRate;
	//上涨平均涨跌幅
	private double avgUpRation;
	//下跌平均涨跌幅
	private double avgDownRation;
	//K线点
	private List<StockKline> stocksklist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAvgRation() {
		return avgRation;
	}
	public void setAvgRation(double avgRation) {
		this.avgRation = avgRation;
	}
	public double getUpRate() {
		return upRate;
	}
	public void setUpRate(double upRate) {
		this.upRate = upRate;
	}
	public double getDownRate() {
		return downRate;
	}
	public void setDownRate(double downRate) {
		this.downRate = downRate;
	}
	public double getAvgUpRation() {
		return avgUpRation;
	}
	public void setAvgUpRation(double avgUpRation) {
		this.avgUpRation = avgUpRation;
	}
	public double getAvgDownRation() {
		return avgDownRation;
	}
	public void setAvgDownRation(double avgDownRation) {
		this.avgDownRation = avgDownRation;
	}
	public List<StockKline> getStocksklist() {
		return stocksklist;
	}
	public void setStocksklist(List<StockKline> stocksklist) {
		this.stocksklist = stocksklist;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "KlineSimResult [id=" + id + ", name=" + name
				+ ", avgRation=" + avgRation
				+ ", upRate=" + upRate + ", downRate="
				+ downRate + ", avgUpRation=" + avgUpRation
				+ ", avgDownRation=" + avgDownRation + ", stocksklist=" + stocksklist.size()
				+"]";
	}

	
}
