package com.lpf.tools;

import com.lpf.tools.base.BaseApplication;

/**
 * Created by liupengfei on 2018/2/5 14:53.
 */

public class MainApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        com.lpf.utilcode.util.Utils.init(this);
    }
}
