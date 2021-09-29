package com.example.navigationlibrary.config;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigSet {

    private String navType;
    private String appid;
    private String appkey;
    private String secretKey;

    private static ConfigSet instence;

    private ConfigSet() {
    }

    public synchronized static ConfigSet getInstence(Context context) {
        if (instence == null) {
            synchronized (ConfigSet.class) {
                if (instence == null) {
                    instence = new ConfigSet();
                    instence.rendConfigSet(context);
                }
            }
        }
        return instence;
    }

    private void rendConfigSet(Context context) {
        Properties properties = new Properties();
        try {
            InputStream in = context.getAssets().open("config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFiledValue(properties);
    }

    private void setFiledValue(Properties properties) {
        navType = properties.getProperty("navType");
        appid = properties.getProperty("appid");
        appkey = properties.getProperty("appkey");
        secretKey = properties.getProperty("secretKey");
    }


    public String getNavType() {
        return navType != null ? navType : "";
    }

    public String getAppid() {
        return appid != null ? appid : "";
    }

    public String getAppkey() {
        return appkey != null ? appkey : "";
    }

    public String getSecretKey() {
        return secretKey != null ? secretKey : "";
    }
}
