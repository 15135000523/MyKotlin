package com.example.mykotlin.utils;

import android.content.Context;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ImageUtils {
    /**
     * 鲁班图片压缩
     * @param path 图片路径
     * @param context 上下文
     * @return 压缩后的图片路径
     */
    public static String compressImage(String path, Context context){
        FileUtils.createOrExistsDir(FileUtils.COMPRESS_PATH);
        final String[] newPath = new String[1];
        Luban.with(context)
                .load(path)
                .ignoreBy(50)
                .setTargetDir(FileUtils.COMPRESS_PATH)
                .setFocusAlpha(false)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() { }
                    @Override
                    public void onSuccess(File file) { newPath[0] = file.getAbsolutePath(); }
                    @Override
                    public void onError(Throwable e) { newPath[0] = path; }
                }).launch();
        return newPath[0];
    }
}
