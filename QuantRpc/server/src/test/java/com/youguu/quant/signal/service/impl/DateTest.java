package com.youguu.quant.signal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SomeBody on 2016/10/12.
 */
public class DateTest {
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");

    public static void main(String[] args) throws ParseException {
        long datetime = 201610120930L;
        Date tradeTime = yyyyMMddHHmm.parse(String.valueOf(datetime));
        String tradeTimeStr = yyyyMMdd.format(tradeTime);
        System.out.println(tradeTimeStr);
    }
}
