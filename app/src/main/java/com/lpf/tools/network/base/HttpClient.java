package com.lpf.tools.network.base;


import com.lpf.tools.network.IRequestUrl;

public class HttpClient {
    private static volatile IRequestUrl instance;
    public static String baseUrl = "http://192.168.10.43:8080";

    public static IRequestUrl getRequestService() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = ServiceGenerator.create(IRequestUrl.class, baseUrl);
                }
            }
        }
        return instance;
    }

}
