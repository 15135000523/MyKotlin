package com.example.mykotlin.designPattern;

import com.example.mykotlin.designPattern.observer.Observable;
import com.example.mykotlin.designPattern.observer.Observer;
import com.example.mykotlin.designPattern.proxyPattern.IProxy;
import com.example.mykotlin.designPattern.proxyPattern.ProxyHandler;
import com.example.mykotlin.designPattern.proxyPattern.User;
import com.example.mykotlin.designPattern.proxyPattern.UserProxy;
import com.example.mykotlin.designPattern.singletonPattern.DateUtils;
import com.tencent.mmkv.MMKV;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.Retrofit;

public class Main {

    public static void main(String[] args) {
//        proxyPattern();
//        System.out.println("----------------单例模式---------------");
//        single();

        observer();
    }

    /**
     * 代理模式
     * 用户去买饭
     */
    private static void proxyPattern() {
        User user = new User();
        UserProxy proxy2 = new UserProxy(user);
        proxy2.byFood();
        System.out.println("----------------下面是动态代理-------------");
        IProxy proxy1 = (IProxy) Proxy.newProxyInstance(user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                new ProxyHandler(user));
        proxy1.byFood();
        System.out.println("--------------- 直接创建动态代理----------------------");
        IProxy proxy3 = (IProxy) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("掉用前的操作");
                method.invoke(user, args);
                System.out.println("掉用后的操作");
                return null;
            }
        });
        proxy3.byFood();
    }

    /**
     * 单例
     */
    private static void single() {
        System.out.println("懒汉：" + DateUtils.getInstance());
        System.out.println("饿汉：" + DateUtils.getInstance1());
        System.out.println("双重校验锁：" + DateUtils.getInstance2());
    }

    private static void observer() {
        Observable observable = new Observable();
        observable.addObserver(new Observer() {
            @Override
            public void notifaDatachange() {
                System.out.println("我收到了");
            }
        });
        observable.notifationChange();

        MMKV mmkv = MMKV.defaultMMKV();
    }
}
