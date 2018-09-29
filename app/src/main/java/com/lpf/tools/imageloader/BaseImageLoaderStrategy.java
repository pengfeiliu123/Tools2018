package com.lpf.tools.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by liupengfei on 2018/2/12 16:27.
 */

public interface BaseImageLoaderStrategy {

    void loadImage(Context context, String url, ImageView imageView);

    void loadImage(Context context, String url, ImageView imageView, int placeholder);

    void loadCircleImage(Context context, String url, ImageView imageView, int placeholder);

    void loadCircleBorderImage(Context context, String url, ImageView imageView, int placeholder, float borderWidth, int borderColor, int height, int width);

    void loadImageWithProgress(Context context, String url, ImageView imageView , int placeholder, ProgressLoadListener listener);

    void loadImageWithProgress(Context context, String url, ProgressLoadListener listener);

    void clearImageDiskCache(final Context context);

    void clearImageMemoryCache(final Context context);

    // 根据不同的内存状态来响应不用的释放策略
    void trimMemory(Context context, int level);

    void loadDrawable(Context context, int drawableId, ImageView imageView);

    Bitmap loadBitmap(Context context, String url);

    void pause(Context context);

    void resume(Context context);

    interface ProgressLoadListener {
        void onResourceReady(Bitmap resource);

        void update(long bytesRead, long contentLength);

        void onException();
    }

}
