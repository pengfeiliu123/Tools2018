package com.lpf.tools.feature.login;

import com.lpf.tools.base.BaseView;
import com.lpf.tools.feature.networkdemo.ResponseEntity;

/**
 * Created by liupengfei on 2018/8/1 15:27.
 */
public interface ILoginView extends BaseView {

    void onLoginSuccess(ResponseEntity response);
}
