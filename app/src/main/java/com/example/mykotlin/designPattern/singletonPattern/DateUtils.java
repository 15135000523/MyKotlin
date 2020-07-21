package com.example.mykotlin.designPattern.singletonPattern;

public class DateUtils {

    private static DateUtils mDateUtils;

    private DateUtils(){}

    /**
     * 懒汉式
     * @return
     */
    public static DateUtils getInstance(){
        if(mDateUtils==null){
            mDateUtils = new DateUtils();
        }
        return mDateUtils;
    }

    /**
     * 饿汉式
     * @return
     */
    private static DateUtils dateUtils = new DateUtils();
    public static DateUtils getInstance1(){
        return  dateUtils;
    }

    /**
     * 双重校验锁
     * @return
     */
    public static synchronized DateUtils getInstance2(){
        if (mDateUtils ==null){
            synchronized (DateUtils.class){
                if (mDateUtils == null) {
                    mDateUtils = new DateUtils();
                }
            }
        }
        return mDateUtils;
    }
}
