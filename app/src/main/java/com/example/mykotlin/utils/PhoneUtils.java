package com.example.mykotlin.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneUtils {

    public static void getPhoneNumber(Activity activity){
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
    }

}
