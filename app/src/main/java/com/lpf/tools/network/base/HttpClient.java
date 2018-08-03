package com.lpf.tools.network.base;


import com.lpf.tools.api.IApiService;

public class HttpClient {
    private static volatile IApiService instance;
    public static String baseUrl = "https://api.douban.com";

    public static IApiService getRequestService() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = ServiceGenerator.create(IApiService.class, baseUrl);
                }
            }
        }
        return instance;
    }

}
