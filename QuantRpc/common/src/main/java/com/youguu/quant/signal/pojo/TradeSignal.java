package com.youguu.quant.signal.pojo;

import java.io.*;
import java.math.BigDecimal;

/**
 * Created by SomeBody on 2016/8/23.
 */
public class TradeSignal implements Serializable,Comparable<TradeSignal>{

    public static final int LENGTH = 25;
    public static final String HS300 = "10000300";
    public static final int PRICE_FACTOR = 10000;

    private int marketId;   //交易市场
    private String stockcode = "";//股票代码
    private long datetime;    //交易时间
    private int price; //交易价格(扩大一万倍)
    private String direct = "";  //交易方向 B.买入 S.卖出 H.持仓
    private int closeprice;//当日收盘价格(扩大一万倍)


    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceFormat() {
        return divide(price,10000,2);
    }

    public String getClosePriceFormat() {
        return divide(closeprice,10000,2);
    }

    /**
     * 小数相除,四舍五入进位
     * @param arg1
     * @param arg2
     * @return
     */
    private static String divide(double arg1,double arg2,int scale)
    {
        BigDecimal bd1 = new BigDecimal(Double.toString(arg1));
        BigDecimal bd2 = new BigDecimal(Double.toString(arg2));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public int getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(int closeprice) {
        this.closeprice = closeprice;
    }



    @Override
    public String toString() {
        return "TradeSignal{" +
                "marketId=" + marketId +
                ", stockcode='" + stockcode + '\'' +
                ", datetime=" + datetime +
                ", price=" + price +
                ", direct='" + direct + '\'' +
                ", closeprice=" + closeprice +
                '}';
    }

    @Override
    public int compareTo(TradeSignal o) {
        return 0-(int)(datetime-o.getDatetime());
    }
}
