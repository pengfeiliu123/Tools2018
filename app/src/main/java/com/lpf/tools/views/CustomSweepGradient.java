package com.lpf.tools.views;

import android.graphics.SweepGradient;
import android.support.annotation.Nullable;

/**
 * Author: lpf
 * Time: 2019/3/6 5:32 PM
 * Description:
 */
public class CustomSweepGradient extends SweepGradient {


    public CustomSweepGradient(float cx, float cy, @Nullable int[] colors, @Nullable float[] positions) {
        super(cx, cy, colors, positions);
    }

    public CustomSweepGradient(float cx, float cy, int color0, int color1) {
        super(cx, cy, color0, color1);
    }


}
