package com.example.navigationlibrary.initializer;

import android.content.Context;

import com.example.navigationlibrary.config.ConfigSet;
import com.example.navigationlibrary.exception.TypeMismatchException;

public class NavInitializer {

    public static void init(Context context) throws TypeMismatchException {
        Initializer initializer;
        if (ConfigSet.getInstence(context).getNavType().contains("bd")) {
            initializer = new BDInitializer();
        } else if (ConfigSet.getInstence(context).getNavType().contains("gd")) {
            initializer = new GDInitializer();
        } else if (ConfigSet.getInstence(context).getNavType().contains("sj")) {
            initializer = new SJInitializer();
        } else {
            throw new TypeMismatchException(" type mismatch, please go assets/config edit navType value as bd, gd or sj.");
        }
        initializer.initialize(context);
    }
}
