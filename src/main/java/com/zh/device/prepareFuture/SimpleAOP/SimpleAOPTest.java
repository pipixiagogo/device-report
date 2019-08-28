package com.zh.device.prepareFuture.SimpleAOP;

import com.zh.device.prepareFuture.SimpleAOP.service.HelloService;
import com.zh.device.prepareFuture.SimpleAOP.service.HelloServiceImpl;

public class SimpleAOPTest {
    public static void main(String[] args) {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
