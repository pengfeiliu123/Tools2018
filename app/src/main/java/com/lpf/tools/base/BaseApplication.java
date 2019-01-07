package com.lpf.tools.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by liupengfei on 2018/2/5 12:42.
 */

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static BaseApplication sInstance;

    public static BaseApplication getsInstance()
    {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        registerActivityLifecycleCallbacks(mCallbacks);

        ForegroundCallbacks.init(this);
        ForegroundCallbacks.get(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                Log.d("lpftag","切换到前台");
            }

            @Override
            public void onBecameBackground() {
                Log.d("lpftag","切换到后台");
            }
        });

    }

    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + "]");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.d(TAG, "onActivityStopped() called with: activity = [" + activity + "]");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.d(TAG, "onActivitySaveInstanceState() called with: activity = [" + activity + "], outState = [" + outState + "]");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG, "onActivityDestroyed() called with: activity = [" + activity + "]");
        }
    };
}
