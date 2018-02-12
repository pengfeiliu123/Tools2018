package com.lpf.tools.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lpf.tools.imageloader.glide.GlideImageLoaderStrategy;

/**
 * Created by liupengfei on 2018/2/12 16:26.
 */

public class ImageLoader {

    private BaseImageLoaderStrategy strategy;

    private ImageLoader() {
        strategy = new GlideImageLoaderStrategy();
    }

    public static ImageLoader getInstance() {
        return SingletonInstanceHolder.INSTANCE;
    }

    private static class SingletonInstanceHolder {
        static ImageLoader INSTANCE = new ImageLoader();
    }

    public void loadImage(Context context, String url, ImageView imageView, int placeholder) {
        strategy.loadImage(context, url, imageView, placeholder);
    }


    public void loadImage(Context context, String url, ImageView imageView) {
        strategy.loadImage(context, url, imageView);
    }


    public void loadCircleImage(Context context, String url, ImageView imageView, int placeholder) {
        strategy.loadCircleImage(context, url, imageView, placeholder);
    }


    public void loadCircleBorderImage(Context context, String url, ImageView imageView, int placeholder, float borderWidth, int borderColor, int height, int width) {
        strategy.loadCircleBorderImage(context, url, imageView, placeholder, borderWidth, borderColor, height, width);
    }


    public void loadImageWithProgress(Context context, String url, ImageView imageView, int placeholder, BaseImageLoaderStrategy.ProgressLoadListener listener) {
        strategy.loadImageWithProgress(context, url, imageView, placeholder, listener);
    }


    public void clearImageDiskCache(Context context) {
        strategy.clearImageDiskCache(context);
    }


    public void clearImageMemoryCache(Context context) {
        strategy.clearImageMemoryCache(context);
    }


    public void trimMemory(Context context, int level) {
        strategy.trimMemory(context, level);
    }


    public void loadDrawable(Context context, int drawableId, ImageView imageView) {
        strategy.loadDrawable(context, drawableId, imageView);
    }


    public Bitmap loadBitmap(Context context, String url) {
        return strategy.loadBitmap(context, url);
    }


    public void pause(Context context) {
        strategy.pause(context);
    }


    public void resume(Context context) {
        strategy.resume(context);
    }
}
