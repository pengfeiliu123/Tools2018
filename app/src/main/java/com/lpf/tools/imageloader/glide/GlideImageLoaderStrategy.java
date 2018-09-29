package com.lpf.tools.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lpf.tools.imageloader.BaseImageLoaderStrategy;

import java.util.concurrent.ExecutionException;

/**
 * Created by liupengfei on 2018/2/12 16:39.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().placeholder(imageView.getDrawable()).dontAnimate())
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, int placeholder) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().placeholder(placeholder).dontAnimate())
                .into(imageView);
    }

    @Override
    public void loadCircleImage(Context context, String url, ImageView imageView, int placeholder) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().placeholder(placeholder).circleCrop())
                .into(imageView);
    }

    @Override
    public void loadCircleBorderImage(Context context, String url, ImageView imageView, int placeholder, float borderWidth, int borderColor, int height, int width) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholder)
                        .transform(new GlideCircleCropWithBorderTransform(context, url, borderWidth, borderColor, height, width)))
                .into(imageView);
    }

    @Override
    public void loadImageWithProgress(Context context, String url, ImageView imageView, int placeholder, final ProgressLoadListener listener) {
        Glide.with(context).asBitmap().load(url)
                .apply(RequestOptions.centerCropTransform()
                        .placeholder(placeholder)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        listener.onException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        listener.onResourceReady(resource);
                        return false;
                    }
                })
                .into(imageView);

    }

    @Override
    public void loadImageWithProgress(Context context, String url, final ProgressLoadListener listener) {
        Glide.with(context).asBitmap().load(url)
                .apply(RequestOptions.centerCropTransform()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        listener.onException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        listener.onResourceReady(resource);
                        return false;
                    }
                });
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context.getApplicationContext()).trimMemory(level);
    }

    @Override
    public void loadDrawable(Context context, int drawableId, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(drawableId)
                .apply(new RequestOptions().dontAnimate())
                .into(imageView);
    }

    @Override
    public void pause(Context context) {
        Glide.with(context).pauseRequestsRecursive();
    }

    @Override
    public void resume(Context context) {
        Glide.with(context).resumeRequestsRecursive();
    }

    @Override
    public Bitmap loadBitmap(Context context, String url) {
        try {
            return Glide.with(context).asBitmap().load(url).submit().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
