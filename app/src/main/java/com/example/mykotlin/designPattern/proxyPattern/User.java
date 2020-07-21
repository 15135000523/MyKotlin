package com.example.mykotlin.designPattern.proxyPattern;

/**
 *
 */
public class User implements IProxy{



    @Override
    public void byFood() {
        System.out.println("拿到饭了，将钱给了代理者");
    }
}
