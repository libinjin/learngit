package com.youguu.quant.strategy.service;

import com.youguu.quant.base.BaseTestClass;
import org.junit.Test;

public class IUserLoopServiceTest extends BaseTestClass {

    IUserLoopService service = (IUserLoopService)getBean("userLoopService");

    @Test
    public void testSaveUserLoop() throws Exception {
        System.out.println(service.saveUserLoop(123,1));
    }

    @Test
    public void testIncrLoopNum() throws Exception {
        System.out.println(service.incrLoopNum(123,1));
    }

    @Test
    public void testIncrFinishNum() throws Exception {
        System.out.println(service.incrBackFinishNum(123, 1, "222"));
    }

    @Test
    public void testGetUserLoop() throws Exception {
        System.out.println(service.getUserLoop(123,0));
    }
    @Test
    public void queryDNANum(){
        System.out.println(service.queryDNANum(123));
    }

    @Test
    public void incDNAFinishNum(){
        service.incDNAFinishNum(123,"11600000");
    }
}