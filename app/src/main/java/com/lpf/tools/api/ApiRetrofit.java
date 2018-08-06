package com.lpf.tools.api;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lpf.tools.network.base.RequestUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liupengfei on 2018/8/1 11:33.
 */
public class ApiRetrofit {

    private static volatile ApiRetrofit apiRetrofit;
    private static final String baseUrl = "https://api.douban.com";

    private IApiService apiServer;
    private static final int TIME_OUT = 10; //second
    private String TAG = "okHttpLog";

    private OkHttpClient client;
    private Retrofit retrofit;

    private ApiRetrofit(){
        client = new OkHttpClient.Builder()
                .addInterceptor(addHeaderInterceptor)
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                // add rxjava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiServer = retrofit.create(IApiService.class);

    }

    public static ApiRetrofit getInstance(){
        if(apiRetrofit == null){
            synchronized (ApiRetrofit.class){
                if(apiRetrofit == null){
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public IApiService getApiService(){
        return apiServer;
    }

    /**
     * 添加Header拦截器
     */
    private Interceptor addHeaderInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            RequestUtil.addHeader(requestBuilder);
            return chain.proceed(requestBuilder.build());
        }
    };

    /**
     * 日志拦截器
     */
    private Interceptor logInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType,content))
                    .build();
        }
    };
}
