package com.lpf.tools;

import com.facebook.stetho.Stetho;
import com.lpf.tools.base.BaseApplication;

/**
 * Created by liupengfei on 2018/2/5 12:42.
 */

public class StethoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
