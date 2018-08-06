package com.lpf.tools.feature.networkdemo;

import com.lpf.tools.base.BaseObserver;
import com.lpf.tools.base.BasePresenter;

/**
 * Created by liupengfei on 2018/8/6 15:15.
 */
public class INetworkPresenter extends BasePresenter<INetworkView>{


    public INetworkPresenter(INetworkView baseView) {
        super(baseView);
    }

    public void doGetDouBanList(int start, int count){
        addDisposable(apiService.getResponseData(start, count), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onGetDouBanSuccess((ResponseEntity)o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
