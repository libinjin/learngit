package com.youguu.quant.klinesim;

import java.util.List;

/**
 * Created by lenovo on 2017/3/16.
 */
public class StockKline {
    private String code;
    private String name;

    private List<CommonKlinePoint> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CommonKlinePoint> getList() {
        return list;
    }

    public void setList(List<CommonKlinePoint> list) {
        this.list = list;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
