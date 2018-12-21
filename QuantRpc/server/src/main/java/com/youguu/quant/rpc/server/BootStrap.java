package com.youguu.quant.rpc.server;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.core.util.PropertiesUtil;
import com.youguu.core.util.rpc.ThriftRpcServer;
import com.youguu.core.zookeeper.broadcast.ZKBroadCastHelper;
import com.youguu.quant.base.ContextLoader;
import com.youguu.quant.rpc.common.MultiplexProp;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * @author zhanglei
        * @ClassName: BootStrap
        * @Description: rpc server 启动入口
        * @date 2014年11月6日 上午11:17:37
        */
public class BootStrap {
    private static final Log logger = LogFactory.getLog(BootStrap.class);

    public static void main(String[] args) {

        ApplicationContext app = new AnnotationConfigApplicationContext(ContextLoader.class);
        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        for (MultiplexProp prop : MultiplexProp.values()) {
            try {
                Constructor<?> cons = prop.getProcessor().getConstructor(prop.getIface());
                processor.registerProcessor(prop.getName(), (TProcessor) cons.newInstance(app.getBean(prop.getImpl())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Properties prop = null;
        try {
            prop = PropertiesUtil.getProperties("properties/quantTrade_rpc_server.properties");
            if (null == prop) {
                logger.error("没有找到启动文件：quantTrade_rpc_server.properties");
                return;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String host = prop.getProperty("quant.rpc.host.name").trim();
        int hostPort = Integer.valueOf(prop.getProperty("quant.rpc.host.port").trim()).intValue();
        int workerThreads = Integer.valueOf(prop.getProperty("quant.rpc.server.workerThreads")).intValue();
        String sectionName = prop.getProperty("quant.rpc.server.sectionName").trim();

        ThriftRpcServer server = new ThriftRpcServer(
                host,
                hostPort,
                workerThreads,
                processor,
                sectionName);


        try {
            //增加广播的监听
            ZKBroadCastHelper.listenerBroadCast();

            server.start();

            logger.info("start on port:{}", hostPort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }


    }

}
