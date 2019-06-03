package com.lpf.tools.feature.glideimage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Px;

/**
 * @author: lpf
 * @time: 2019-06-03 14:06
 * @description:
 */
@SuppressWarnings("unused")
public class DrawableCreator {


    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    private @ColorInt
    int mSolidColor, mStrokeColor;

    private float mCornerRadiusDp;

    private float mStrokeWidthDp;



    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    /**
     * 创建一个指定圆角半径和颜色的 Drawable
     */
    @NonNull
    public static Drawable create(@ColorInt int color, @Px int cornerRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(cornerRadius);
        return drawable;
    }

    /**
     * 创建一个指定圆角半径和颜色的 Drawable
     */
    @NonNull
    public static Drawable create(String color, @Px int cornerRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(color));
        drawable.setCornerRadius(cornerRadius);
        return drawable;
    }

    /**
     * 创建一个指定圆角半径和颜色、指定边框的 Drawable
     */
    @NonNull
    public static Drawable create(@ColorInt int color,
        @Px int cornerRadius,
        @Px int borderWidth,
        @ColorInt int borderColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(cornerRadius);
        drawable.setStroke(borderWidth, borderColor);
        return drawable;
    }

    /**
     * 创建一个指定圆角半径和颜色、指定边框的 Drawable
     */
    @NonNull
    public static Drawable create(@Px int cornerRadius,
        @Px int borderWidth,
        @ColorInt int borderColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(cornerRadius);
        drawable.setStroke(borderWidth, borderColor);
        return drawable;
    }




    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public Drawable build(@NonNull Context context) {

        float density = context.getResources().getDisplayMetrics().density;

        GradientDrawable drawable = new GradientDrawable();
        return create(
            mSolidColor,
            (int)(density * mCornerRadiusDp),
            (int)(density * mStrokeWidthDp),
            mStrokeColor
        );
    }




    /* ======================================================= */
    /* Getter / Setters                                        */
    /* ======================================================= */

    public DrawableCreator setSolidColor(int solidColor) {
        mSolidColor = solidColor;
        return this;
    }

    public DrawableCreator setStrokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
        return this;
    }

    public DrawableCreator setStrokeColor(String strokeColor) {
        mStrokeColor = Color.parseColor(strokeColor);
        return this;
    }

    public DrawableCreator setCornerRadiusDp(float cornerRadiusDp) {
        mCornerRadiusDp = cornerRadiusDp;
        return this;
    }

    public DrawableCreator setStrokeWidthDp(float strokeWidthDp) {
        mStrokeWidthDp = strokeWidthDp;
        return this;
    }
}

