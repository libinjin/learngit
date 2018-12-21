package com.youguu.quant.rpc.server.signal;

import com.youguu.quant.base.BaseTestClass;
import org.apache.thrift.TException;
import org.junit.Test;

public class SignalServiceThriftRpcServiceImplTest extends BaseTestClass {
    SignalServiceThriftRpcServiceImpl service = (SignalServiceThriftRpcServiceImpl) getBean("signalServiceThriftRpcService");

    @Test
    public void testQueryPageStrategyTradeRecord() throws TException {
        service.queryPageStrategyTradeRecord(2235, 3298, 2, 1, 10, "11603897,21002931,21300624");
    }

}