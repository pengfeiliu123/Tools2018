package com.lpf.tools.base;

/**
 * Created by liupengfei on 2018/8/1 14:08.
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String msg);

    void onErrorCode(BaseModel model);
}
