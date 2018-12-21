package com.youguu.quant.aspect;

import com.youguu.core.logging.Log;
import com.youguu.core.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by    on 2014/9/30.
 */
@Aspect
@Component
public class RpcExecuteTimeAspect {
    private static final Log log = LogFactory.getLog("time");

    @Around("execution(* com.youguu.quant.rpc.server.*.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;

        log.info(String.format("[%s] %s", costTime, joinPoint.toShortString()));

        return result;
    }
}
