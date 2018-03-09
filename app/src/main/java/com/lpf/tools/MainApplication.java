package com.lpf.tools;

import com.facebook.stetho.Stetho;
import com.lpf.tools.base.BaseApplication;

import org.litepal.LitePal;

/**
 * Created by liupengfei on 2018/2/5 14:53.
 */

public class MainApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        com.lpf.utilcode.util.Utils.init(this);
        LitePal.initialize(this);
    }
}
