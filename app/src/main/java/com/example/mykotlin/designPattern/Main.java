package com.example.mykotlin.designPattern;


import android.util.Base64;

import com.example.mykotlin.designPattern.encode.TextEncode;
import com.example.mykotlin.designPattern.observer.Observable;
import com.example.mykotlin.designPattern.proxyPattern.IProxy;
import com.example.mykotlin.designPattern.proxyPattern.ProxyHandler;
import com.example.mykotlin.designPattern.proxyPattern.User;
import com.example.mykotlin.designPattern.proxyPattern.UserProxy;
import com.example.mykotlin.designPattern.singletonPattern.DateUtils;
import com.example.mykotlin.http.BaseResponse;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.Key;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

//        String a = new String("123");
//        String b = new String("123");
//        String c = b;
//        c.hashCode();
//
//        System.out.println("a==b==" + (a.equals(b) ) + ",b==c==" + (b .equals(c)) + ",a==c==" + (a .equals(c)));
//        takeCommitKeyValue(new BaseResponse<String>("200", "请求成功", "这是数据", true));

        //加密解密
        TextEncode textEncode = TextEncode.getInstance();
//        textEncode.textDESEncode("闫文浩真帅");
        textEncode.textAES("闫文浩真帅");
//        String str = "这是一个神奇的字符串wrq";
//        textEncode.textRSA();
//        byte[] encodeStr = textEncode.encryptByPublicKey(str.getBytes(), textEncode.getKeybyte(TextEncode.PUBLIC_KEY));
//        byte[] decodeStr = textEncode.decryptByPrivateKey(encodeStr, textEncode.getKeybyte(TextEncode.PRIVATE_KEY));
//        System.out.println("字符:" + str + "\n 私钥加密:" + new String(encodeStr) + "\n 公钥解密:" + new String(decodeStr));
//
//        System.out.println("\n");
//        byte[] encodeStra = textEncode.encode(str.getBytes());
//        byte[] decodeStra = textEncode.decode(encodeStra);
//        System.out.println("字符:" + str + "\n 私钥加密:" + new String(encodeStra) + "\n 公钥解密:" + new String(decodeStra));
    }

    /**
     * 将传入的bean转换成对应的请求的body
     *
     * @param bean 请求的类
     * @param <T>
     * @return 请求的表单
     */
    public static <T> List<MultipartBody.Part> takeCommitKeyValue(T bean) {
        List parts = new ArrayList<MultipartBody.Part>();
        Class cl = bean.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().contains("Path")) {
                    File file = new File(String.valueOf(field.get(bean)));
                    parts.add(MultipartBody.Part.createFormData(field.getName(), file.getName(), RequestBody.create(MediaType.parse("image/png"), file)));
                } else {
                    parts.add(MultipartBody.Part.createFormData(field.getName(), String.valueOf(field.get(bean))));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(parts.toString());
        return parts;
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

    private static int selectIntFor(int value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value)
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
