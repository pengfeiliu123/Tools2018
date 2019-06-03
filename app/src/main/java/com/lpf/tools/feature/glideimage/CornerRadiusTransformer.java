package com.lpf.tools.feature.glideimage;

import java.security.MessageDigest;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

/**
 * 圆角设置，配合Glide加载xml中图片的scaleType为centerCrop
 */
public class CornerRadiusTransformer extends BitmapTransformation {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    @Px
    private int mRadiusPx;



    /* ======================================================= */
    /* Constructors                                            */
    /* ======================================================= */

    public CornerRadiusTransformer(@Px int radiusPx) {
        mRadiusPx = radiusPx;
    }



    /* ======================================================= */
    /* Override/Implements Methods                             */
    /* ======================================================= */

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap source, int outWidth, int outHeight) {

        // 先 CenterCrop
        Bitmap bitmap = TransformationUtils.centerCrop(pool, source, outWidth, outHeight);

        // 获取一个可复用的 Drawable，没有会自动创建
        Bitmap reuse = pool.get(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

        // 绘制圆角
        Canvas canvas = new Canvas(reuse);

        Paint paint = new Paint();

        // 蒙版
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        RectF rect = new RectF(0, 0, outWidth, outHeight);
        canvas.drawRoundRect(rect, mRadiusPx, mRadiusPx, paint);
        return reuse;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
