package com.youguu.quant.signal.service.impl;

import com.youguu.quant.signal.pojo.TradeSignal;
import com.youguu.quant.util.FileUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by SomeBody on 2016/9/7.
 */
public class FileTest {
    public static void main(String[] args) throws Exception {
        long time = 201611010925L;
        System.out.println(time/10000);
        System.out.println(time%10000);


    }

    @Test
    public void testPrint(){
        String filePath = "E:\\data\\quant\\1111\\21002751.dat";
        try {
            FileUtil.print(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadInterval(){
        String filePath = "E:\\data\\quant\\2222\\10000300.dat";
        List<TradeSignal> list = FileUtil.readInterval(filePath, 20150927, 20160927);
        System.out.println(list.size());
        System.out.println(list);
    }
}
