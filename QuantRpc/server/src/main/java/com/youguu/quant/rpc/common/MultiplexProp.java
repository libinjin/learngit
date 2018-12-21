package com.youguu.quant.rpc.common;


import com.youguu.quant.rpc.server.klinesim.KlineServiceThriftRpcServiceImpl;
import com.youguu.quant.rpc.server.signal.SignalServiceThriftRpcServiceImpl;
import com.youguu.quant.rpc.server.strategy.StrategyServiceThriftRpcServiceImpl;
import com.youguu.quant.rpc.thrift.gen.SignalServiceThriftRpcService;
import com.youguu.quant.rpc.thrift.gen.StrategyServiceThriftRpcService;
import com.youguu.quant.rpc.thrift.gen.KLineServiceThriftRpcService;

public enum MultiplexProp {

    StrategyServiceThriftRpcService("StrategyServiceThriftRpcService",
            StrategyServiceThriftRpcService.Client.class,
            StrategyServiceThriftRpcService.Processor.class,
            StrategyServiceThriftRpcService.Iface.class,
            StrategyServiceThriftRpcServiceImpl.class),
    SignalServiceThriftRpcService("SignalServiceThriftRpcService",
            SignalServiceThriftRpcService.Client.class,
            SignalServiceThriftRpcService.Processor.class,
            SignalServiceThriftRpcService.Iface.class,
            SignalServiceThriftRpcServiceImpl.class),
    KLineServiceThriftRpcService("KLineServiceThriftRpcService",
    		KLineServiceThriftRpcService.Client.class,
    		KLineServiceThriftRpcService.Processor.class,
    		KLineServiceThriftRpcService.Iface.class,
    		KlineServiceThriftRpcServiceImpl.class);

    Class<?> processor;

    Class<?> client;

    Class<?> iface;

    Class<?> impl;

    private String name;

    MultiplexProp(String name, Class<?> client, Class<?> processor, Class<?> iface, Class<?> impl) {
        this.processor = processor;
        this.client = client;
        this.name = name;
        this.iface = iface;
        this.impl = impl;
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

    public Class<?> getImpl() {
        return impl;
    }

    public void setImpl(Class<?> impl) {
        this.impl = impl;
    }
    }
