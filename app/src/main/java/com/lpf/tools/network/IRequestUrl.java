package com.lpf.tools.network;

import com.lpf.tools.feature.networkdemo.ResponseEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liupengfei on 2018/2/9 18:13.
 */

public interface IRequestUrl {

    @GET("/test/1.json")
    Observable<ResponseEntity> getResponseData();

//    @GET(API_VERSION + "/user/subscribe")
//    Observable<ChannelGetResponse> getChannel();
//
//    @POST(API_VERSION + "/user/subscribe")
//    Completable uploadChannels(@Body ArrayList<String> ids);
//
//    @GET(API_VERSION + "/feed/{feedId}")
//    Observable<DetailResponse> getDetailResponse(@Path("feedId") String feedId, @Query("iswifi") String iswifi, @Query("recm") String recm,
//
//    @GET(API_VERSION + "/user/subscribedomain/{domainId}")
//    Observable<GroupDomain> getGroupDomain(@Path("domainId") String domainId);
//
//    @POST(API_VERSION + "/feed/action")
//    Observable<Response<FeedActionResponse[]>> postFeedAction(@Body FeedActionRequest feedActionRequest);
//
//    @POST(API_VERSION + "/inform")
//    Completable postReport(@Body ReportRequest reportRequest);

}
