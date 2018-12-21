package com.youguu.quant.aspect;

import com.youguu.cache.aop.CacheIntercept;
import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import com.youguu.quant.rpc.common.Constants;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by xyj on 2016/12/2.
 */
@Aspect
@Component("strategyCacheIntercept")
public class StrategyCacheInterceptImpl extends CacheIntercept {

    private static final Log logger = LogFactory.getLog(Constants.QUANT_RPC_SERVER);

    @Pointcut("execution(* com.youguu.quant.rpc.server.strategy..*.*(..))|| execution(* com.youguu.quant.strategy.service.impl..*.*(..))")
    @Override
    public void pointcut() {
        logger.info("--------------strategyCacheIntercept--------------");
    }

}
