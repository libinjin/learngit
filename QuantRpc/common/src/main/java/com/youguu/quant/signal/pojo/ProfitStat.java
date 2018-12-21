package com.youguu.quant.signal.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by lenovo on 2016/11/9.
 * 盈利率的统计数据
 */
public class ProfitStat {
    /**
     * 策略id
     */
    private int strategyId;
    /**
     * 策略名称
     */
    private String name;

    /**
     * 总盈利情况
     */
    private ProfitBean total_ProfitBean;

    /**
     * 卖出盈利情况
     */
    private ProfitBean sale_ProfitBean;

    /**
     * 日盈利买入明细情况
     */
    private Map<Integer,ProfitBean> day_ProfitBean = new LinkedHashMap<>();


    class ProfitBean{
        //yyyyMMdd
        int date;
        double win_profilt;
        int win_num;
        double loss_profilt;
        int loss_num;

        List<JSONObject> stocks =new ArrayList<>();

        public int getDate() {
            return date;
        }

        public double getWin_profilt() {
            return win_profilt;
        }

        public int getWin_num() {
            return win_num;
        }

        public double getLoss_profilt() {
            return loss_profilt;
        }

        public int getLoss_num() {
            return loss_num;
        }

        public List<JSONObject> getStocks() {
            return stocks;
        }

        public void setStocks(List<JSONObject> stocks) {
            this.stocks = stocks;
        }
    }

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfitBean getTotal_ProfitBean() {
        return total_ProfitBean;
    }

    public void setTotal_ProfitBean(ProfitBean total_ProfitBean) {
        this.total_ProfitBean = total_ProfitBean;
    }

    public ProfitBean getSale_ProfitBean() {
        return sale_ProfitBean;
    }

    public void setSale_ProfitBean(ProfitBean sale_ProfitBean) {
        this.sale_ProfitBean = sale_ProfitBean;
    }

    public Map<Integer, ProfitBean> getDay_ProfitBean() {
        return day_ProfitBean;
    }

    public void setDay_ProfitBean(Map<Integer, ProfitBean> day_ProfitBean) {
        this.day_ProfitBean = day_ProfitBean;
    }

    /**
     *
     * @param date      yyyyMMdd
     * @param profilt   盈利
     * @param type      B,S
     *
     */
    public void countProfilt(int date,double profilt,String type,String code){
//        if("H".equals(type)) {
//            type = "B";
//        }
        ProfitBean day = null; //当日汇总明细
        if(total_ProfitBean==null){
            total_ProfitBean = new ProfitBean();
        }

        if("S".equals(type) && sale_ProfitBean==null){
            sale_ProfitBean = new ProfitBean();
        }

        if("B".equals(type)){
            day = day_ProfitBean.get(date);
            if(day==null){
                day = new ProfitBean();
                day.date = date;
                day_ProfitBean.put(date,day);

            }
        }

        if(profilt>=0){
            total_ProfitBean.win_num += 1;
            total_ProfitBean.win_profilt += profilt;
            JSONObject stock = new JSONObject();
            stock.put("code",code);
            stock.put("profilt",profilt);
            if("S".equals(type)){
                sale_ProfitBean.win_num += 1;
                sale_ProfitBean.win_profilt += profilt;
                sale_ProfitBean.stocks.add(stock);
            }else{
                day.win_num += 1;
                day.win_profilt += profilt;
                day.stocks.add(stock);
            }
        }else{
            total_ProfitBean.loss_num += 1;
            total_ProfitBean.loss_profilt += profilt;
            JSONObject stock = new JSONObject();
            stock.put("code",code);
            stock.put("profilt",profilt);
            if("S".equals(type)){
                sale_ProfitBean.loss_num += 1;
                sale_ProfitBean.loss_profilt += profilt;
                sale_ProfitBean.stocks.add(stock);
            }else{
                day.loss_num += 1;
                day.loss_profilt += profilt;
                day.stocks.add(stock);
            }
        }
    }


    public String toJson(){
        JSONObject json = new JSONObject();
        json.put("id",strategyId);
        json.put("name",name);
        json.put("total",total_ProfitBean);
        Collection<ProfitBean> coll = day_ProfitBean.values();
        List<ProfitBean> list = new ArrayList<>();
        list.addAll(coll);
        Collections.sort(list,new Comparator<ProfitStat.ProfitBean>() {
            @Override
            public int compare(ProfitStat.ProfitBean o1, ProfitStat.ProfitBean o2) {
                return o1.date >= o2.date ? -1 : 1;
            }
        });

        JSONArray ja = new JSONArray();
        for(int i=0;i<list.size();i++){
            ProfitStat.ProfitBean pb = list.get(i);
            Collections.sort(pb.stocks, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    return o1.getDouble("profilt") >= o2.getDouble("profilt") ? -1 : 1;
                }
            });
            if(pb.getStocks().size()>5){
                pb.stocks = pb.getStocks().subList(0,5);
            }
            ja.add(pb);
            if(i>=4) break;
        }
        json.put("day",ja);

        if(sale_ProfitBean!=null && sale_ProfitBean.stocks.size()>0){
            Collections.sort(sale_ProfitBean.stocks, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    return o1.getDouble("profilt") >= o2.getDouble("profilt") ? -1 : 1;
                }
            });
            if(sale_ProfitBean.getStocks().size()>5){
                sale_ProfitBean.stocks = sale_ProfitBean.getStocks().subList(0,5);
            }
            json.put("sell",sale_ProfitBean);

        }


        return json.toJSONString();
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        ProfitBean pb = new ProfitStat().new ProfitBean();
        pb.date = 1;
        json.put("total",pb);
        json.put("sell",1);
        System.out.println(json.toJSONString());
    }

}
