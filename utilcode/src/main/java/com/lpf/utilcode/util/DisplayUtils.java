package com.lpf.utilcode.util;


import android.content.Context;

/**
 * Created by ranbin on 16/8/9.
 */

public class DisplayUtils {

    private static float OFFSET = 0.5f;


    public static int dip2Px(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px * density + OFFSET*(px >= 0?1:-1));
    }

    public static int px2Dip(Context context,int dip){
        float density = context.getResources().getDisplayMetrics().density;
        if(density!=0){
            return (int) (dip / density + OFFSET*(dip >= 0?1:-1));
        }

        return dip;
    }


    public static int sp2Px(Context context,int px){
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px * scaleDensity + OFFSET);
    }

    public static int px2Sp(Context context,int sp){
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        if(scaleDensity == 0){
            return sp;
        }
        return (int) (sp / scaleDensity - OFFSET);
    }



}
