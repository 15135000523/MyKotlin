package com.example.mykotlin.designPattern.proxyPattern;

public class UserProxy implements IProxy {
    private User user;

    public UserProxy(User user) {
        this.user = user;
    }

    @Override
    public void byFood() {
        System.out.println("被人招呼买饭，拿到买饭信息");
        user.byFood();
        System.out.println("确认用户拿到了饭，并且客户已经结单");
    }
}
