package com.lpf.tools.network.base;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientFactory {

    //如果没有特殊需求，应该复用一个OKHttpClient
    public static OkHttpClient create() {
        return InstanceHolder.INSTANCE;
    }

    //如果需求的OKHttpClient实例跟全局复用的实例不一致，需要创建新的实例
    //时间全是毫秒级别
    public static OkHttpClient createNew(long connectTimeOut, long readTimeOut, long writeTimeOut) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder requestBuilder = request.newBuilder();
                        RequestUtil.addHeader(requestBuilder);
                        return chain.proceed(requestBuilder.build());
                    }
                });
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
        return builder.build();
    }

    private static class InstanceHolder {
        static OkHttpClient INSTANCE = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder requestBuilder = request.newBuilder();
                        RequestUtil.addHeader(requestBuilder);
                        return chain.proceed(requestBuilder.build());
                    }
                })
                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();
    }
}
