package com.youguu.quant.strategy.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created by qxd on 2017/2/15.
 */
public class StrategySearch {
    //左侧文案
    private String titleLeft;
    //右侧文案
    private String titleRight;
    //右侧文案跳转地址
    private String titleRightUrl;
    //key 策略id value 策略最优股票集合
    private List<StrategyBestStock> list;

    public String getTitleLeft() {
        return titleLeft;
    }

    public void setTitleLeft(String titleLeft) {
        this.titleLeft = titleLeft;
    }

    public String getTitleRight() {
        return titleRight;
    }

    public void setTitleRight(String titleRight) {
        this.titleRight = titleRight;
    }

    public String getTitleRightUrl() {
        return titleRightUrl;
    }

    public void setTitleRightUrl(String titleRightUrl) {
        this.titleRightUrl = titleRightUrl;
    }

    public List<StrategyBestStock> getList() {
        return list;
    }

    public void setList(List<StrategyBestStock> list) {
        this.list = list;
    }
}
