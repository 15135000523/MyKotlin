package com.example.mykotlin.designPattern;

import com.example.mykotlin.designPattern.observer.Observable;
import com.example.mykotlin.designPattern.proxyPattern.IProxy;
import com.example.mykotlin.designPattern.proxyPattern.ProxyHandler;
import com.example.mykotlin.designPattern.proxyPattern.User;
import com.example.mykotlin.designPattern.proxyPattern.UserProxy;
import com.example.mykotlin.designPattern.singletonPattern.DateUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
//        System.out.println("----------------代理模式---------------");
//        proxyPattern();
//        System.out.println("----------------单例模式---------------");
//        single();
//        System.out.println("----------------观察者模式---------------");
//        observer();

//        getList();
//        System.out.println("start time :"+new Date().getTime());
//        System.out.println(selectInt(178888,0,list.size()-1));
//        System.out.println(selectIntFor(178888));
//        System.out.println("start time :"+new Date().getTime());

        String a = new String("123");
        String b = new String("123");
        String c = b;
        c.hashCode();

        System.out.println("a==b==" + (a.equals(b) ) + ",b==c==" + (b .equals(c)) + ",a==c==" + (a .equals(c)));
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
        System.out.println(DateUtils.getInstance() == DateUtils.getInstance2());
    }

    /**
     * 观察者模式
     */
    private static void observer() {
        Observable observable = new Observable();
        observable.addObserver(() -> {
            System.out.println("我收到了观察的信息");
        });
        observable.addObserver(() -> {
            System.out.println("这个消息很震惊");
        });
        observable.addObserver(() -> {
            System.out.println("你说什么，总理没了addObserverdate public static void observer(){" +
                    "System.out.println}");
        });
        observable.notifationChange();
    }

    private static ArrayList<Integer> list;

    private static void getList() {
        list = new ArrayList<>();
        for (int i = 1; i < 90001; i++) {
            list.add(i * 2);
        }
    }

    private static int selectIntFor(int value){
        for (int i=0;i<list.size();i++) {
            if (list.get(i)==value)
            return i;
        }
        return -1;
    }

    private static int selectInt(int value, int low, int high) {
        if (value < list.get(low) || value > list.get(high) || low > high)
            return -1;
        int middle = (low + high) / 2;
        if (value > list.get(middle)) {
            return selectInt(value, middle, high);
        } else if (value < list.get(middle)) {
            return selectInt(value, low, middle);
        } else
            return middle;
    }
}
