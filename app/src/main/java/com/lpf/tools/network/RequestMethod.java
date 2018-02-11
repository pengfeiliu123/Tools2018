package com.lpf.tools.network;

import com.lpf.tools.feature.networkdemo.ResponseEntity;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.lpf.tools.network.base.HttpClient.getRequestService;

/**
 * Created by liupengfei on 2018/2/9 19:09.
 */

public class RequestMethod {

    private RequestMethod(){}

    private static class SingletonHolder{
        private static final RequestMethod INSTANCE = new RequestMethod();
    }

    public static RequestMethod getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public Observable<ResponseEntity> getResponseData(){
        return getRequestService().getResponseData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }

//    public Observable<ChannelGetResponse> getChannel() {
//        return getNewsService().getChannel()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io());
//    }

    //******************** post method *******************

//    public Observable<SessionResponse> postSessionToServer(SignInRequest newsListRequest) {
//        return HttpClient.getNewsService().postSessionToServer(newsListRequest)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
