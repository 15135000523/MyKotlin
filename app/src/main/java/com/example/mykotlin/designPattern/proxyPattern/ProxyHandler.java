package com.example.mykotlin.designPattern.proxyPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private Object agent;

    public ProxyHandler(Object agent) {
        this.agent = agent;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(agent,args);
        after();
        return null;
    }

    private void before(){
        System.out.println("执行业务前的包装");
    }

    private void after(){
        System.out.println("执行业务后的处理");
    }

}
