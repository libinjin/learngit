package com.youguu.quant.rpc.common;

import com.youguu.quant.rpc.thrift.gen.StrategyServiceThriftRpcService;


public enum ClientMultiplexProp {

    StrategyServiceThriftRpcService("StrategyServiceThriftRpcService",
            StrategyServiceThriftRpcService.Client.class,
            StrategyServiceThriftRpcService.Processor.class,
            StrategyServiceThriftRpcService.Iface.class),
    SignalServiceThriftRpcService("SignalServiceThriftRpcService",
            com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService.Client.class,
            com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService.Processor.class,
            com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService.Iface.class),
    KLineServiceThriftRpcService("KLineServiceThriftRpcService",
            com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService.Client.class,
            com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService.Processor.class,
            com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService.Iface.class);


    Class<?> processor;

    Class<?> client;

    Class<?> iface;

    private String name;

    ClientMultiplexProp(String name, Class<?> client, Class<?> processor, Class<?> iface) {
        this.processor = processor;
        this.client = client;
        this.name = name;
        this.iface = iface;
    }

    public Class<?> getProcessor() {
        return processor;
    }

    public void setProcessor(Class<?> processor) {
        this.processor = processor;
    }

    public Class<?> getClient() {
        return client;
    }

    public void setClient(Class<?> client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getIface() {
        return iface;
    }

    public void setIface(Class<?> iface) {
        this.iface = iface;
    }

}
