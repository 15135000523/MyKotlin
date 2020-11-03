package com.example.mykotlin.utils;

import android.graphics.Color;
import android.util.Log;

public class LinearGradientUtil {
    private int mStartColor;
    private int mEndColor;

    public LinearGradientUtil(int startColor, int endColor) {
        this.mStartColor = startColor;
        this.mEndColor = endColor;
    }

    public void setStartColor(int startColor) {
        this.mStartColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.mEndColor = endColor;
    }

    public int getColor(float radio) {
        int redStart = Color.red(mStartColor);
        int blueStart = Color.blue(mStartColor);
        int greenStart = Color.green(mStartColor);
        int redEnd = Color.red(mEndColor);
        int blueEnd = Color.blue(mEndColor);
        int greenEnd = Color.green(mEndColor);

        int red = (int) (redStart + ((redEnd - redStart) * radio ));
        int greed = (int) (greenStart + ((greenEnd - greenStart) * radio));
        int blue = (int) (blueStart + ((blueEnd - blueStart) * radio ));
        return Color.argb(255,red, greed, blue);
    }
}