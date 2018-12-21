package com.youguu.quant;

import com.youguu.quote.pojo.CurStatus;
import com.youguu.quote.rpc.client.QuoteClient;

public class Test {
    public static void main(String[] args) {
        CurStatus curStatus = QuoteClient.getCurStatusByCode("11732302");
        System.out.println(curStatus);
    }
}
