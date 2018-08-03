package com.lpf.tools.api;

import com.lpf.tools.feature.networkdemo.ResponseEntity;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by liupengfei on 2018/2/9 18:13.
 */

public interface IApiService {

    @GET("/v2/movie/top250")
    Observable<ResponseEntity> getResponseData(@Query("start") int startPos, @Query("count") int count);


    @POST("shopping_login.htm")
    Observable<String> LoginByRx(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("user/register.do")
    Observable<String> register(@Part("phone") RequestBody phone, @Part("password") RequestBody password, @Part MultipartBody.Part image);

    //    @Streaming
    @GET
    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

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
