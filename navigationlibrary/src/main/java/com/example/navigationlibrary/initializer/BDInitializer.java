package com.example.navigationlibrary.initializer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.baidu.navisdk.adapter.struct.BNTTsInitConfig;
import com.baidu.navisdk.adapter.struct.BNaviInitConfig;
import com.example.navigationlibrary.config.ConfigSet;

import java.io.File;

public class BDInitializer implements Initializer {
    @Override
    public void initialize(Context context) {
        //
        SDKInitializer.setApiKey(ConfigSet.getInstence(context).getAppkey());
        SDKInitializer.initialize(context);
        SDKInitializer.setCoordType(CoordType.BD09LL);

        initNavi(context);
    }

    private void initNavi(Context context) {
        BNaviInitConfig config = new BNaviInitConfig.Builder()
                .appFolderName("YAN")
                .sdcardRootPath(context.getExternalFilesDir(null).getPath())
                .naviInitListener(new IBaiduNaviManager.INaviInitListener() {
                    @Override
                    public void onAuthResult(int i, String s) {
                        String authinfo = "";
                        if (0 == i) {
                            authinfo = "key校验成功!";
                        } else {
                            authinfo = "key校验失败, " + s;
                        }
                        Log.e("YAN", authinfo);
                    }

                    @Override
                    public void initStart() {

                    }

                    @Override
                    public void initSuccess() {
                        BaiduNaviManagerFactory.getBaiduNaviManager().enableOutLog(true);
                        String cuid = BaiduNaviManagerFactory.getBaiduNaviManager().getCUID();
                        Log.e("YAN", "cuid = " + cuid);
                        initTTS(context);
                        context.sendBroadcast(new Intent("com.navi.ready"));
                    }

                    @Override
                    public void initFailed(int i) {

                    }
                }).build();
        if (!BaiduNaviManagerFactory.getBaiduNaviManager().isInited()) {
            BaiduNaviManagerFactory.getBaiduNaviManager().init(context, config);
        }
    }

    private void initTTS(Context context) {
        // 使用内置TTS
        BNTTsInitConfig config = new BNTTsInitConfig.Builder()
                .context(context.getApplicationContext())
                .sdcardRootPath(getSdcardDir(context))
                .appFolderName("YAN")
                .appId(ConfigSet.getInstence(context).getAppid())
                .appKey(ConfigSet.getInstence(context).getAppkey())
                .secretKey(ConfigSet.getInstence(context).getSecretKey())
                .build();
        BaiduNaviManagerFactory.getTTSManager().initTTS(config);
    }

    private String getSdcardDir(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            // 如果外部储存可用 ,获得外部存储路径
            File file = context.getExternalFilesDir(null);
            if (file != null && file.exists()) {
                return file.getPath();
            } else {
                return context.getFilesDir().getPath();
            }
        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }
}
