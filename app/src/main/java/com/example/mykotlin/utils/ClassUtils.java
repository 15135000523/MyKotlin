package com.example.mykotlin.utils;

public class ClassUtils {

    private static ClassUtils instance;
    private ClassUtils(){}

    public static ClassUtils getInstance(){
        if (instance==null){
            synchronized (ClassUtils.class){
                if (instance==null){
                    instance = new ClassUtils();
                }
            }
        }
        return instance;
    }

    public String getClassName(Class c){
        return c.getName();
    }
}
