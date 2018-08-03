package com.lpf.tools.mvpdemo.login;

import com.lpf.tools.base.BaseObserver;
import com.lpf.tools.base.BasePresenter;

/**
 * Created by liupengfei on 2018/8/1 15:28.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView baseView) {
        super(baseView);
    }

    public void doLogin(String name, String password) {
//        addDisposable(apiService.LoginByRx(name, password), new BaseObserver(baseView) {
////            @Override
////            public void onSuccess(Object o) {
////                baseView.onLoginSuccess();
////            }
////
////            @Override
////            public void onError(String msg) {
////                baseView.showError(msg);
////            }
////        });

        addDisposable(apiService.getResponseData(0, 10), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSuccess();
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });

    }
}
