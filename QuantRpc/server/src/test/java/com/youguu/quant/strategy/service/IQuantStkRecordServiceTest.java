package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import com.youguu.quant.strategy.pojo.QuantStkRecord;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IQuantStkRecordServiceTest extends BaseTestClass {

    IQuantStkRecordService service = (IQuantStkRecordService)getBean("quantStkRecordService");

    @Test
    public void testSaveQuantStkRecord() throws Exception {
        QuantStkRecord qs = new QuantStkRecord();
        qs.setRid(1);
        qs.setSid(2);
        qs.setStockCode("aaa");
        qs.setStockName("bbb");
        service.saveQuantStkRecord(qs);
    }

    @Test
    public void testIfExists() throws Exception {
        System.out.println(service.ifExists(2,2));
    }

    @Test
    public void testDisposeQuantStk() throws Exception {
        String time = "2016-09-30 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        System.out.println(service.disposeQuantStk(2222, date));
    }

}