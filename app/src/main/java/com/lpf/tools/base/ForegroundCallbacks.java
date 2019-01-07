package com.lpf.tools.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by liupengfei on 2019/1/7 15:56.
 */
public class ForegroundCallbacks implements Application.ActivityLifecycleCallbacks{

    public static final long CHECK_DELAY = 500;
    public static final String TAG = ForegroundCallbacks.class.getName();

    public interface Listener{
        void onBecameForeground();
        void onBecameBackground();
    }

    private static ForegroundCallbacks instance;
    private boolean foreground = false, paused = true;
    private Handler handler = new Handler();
    private List<Listener> listeners = new CopyOnWriteArrayList<Listener>();
    private Runnable check;

    public static ForegroundCallbacks init(Application application){
        if(instance == null){
            instance = new ForegroundCallbacks();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Application application){
        if(instance == null){
            init(application);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Context ctx){
        if(instance == null){
            Context appCtx = ctx.getApplicationContext();
            if( appCtx instanceof Application){
                init((Application) appCtx);
            }
            throw new IllegalStateException("Foreground is not initialised " +
                    "and cannot obtain the Application object");
        }
        return instance;
    }

    public static ForegroundCallbacks get(){
        if( instance == null){
            throw new IllegalStateException("Foreground is not initialised - invoke " +
                    "at least once with parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground(){
        return foreground;
    }

    public boolean isBackground(){
        return !foreground;
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");
        paused = false;
        boolean wasBackground = !foreground;
        foreground = true;
        if (check!=null){
            handler.removeCallbacks(check);
        }
        if(wasBackground){
            Log.d("lpftag","went foreground");
            for(Listener listener: listeners){
                try {
                    listener.onBecameForeground();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("lpftag","Listener threw exception:"+e.toString());
                }
            }
        }else{
            Log.d("lpftag","still foreground");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
        paused = true;
        if(check!=null){
            handler.removeCallbacks(check);
        }
        handler.postDelayed(check = new Runnable() {
            @Override
            public void run() {
                if(foreground && paused){
                    foreground = false;
                    Log.d("lpftag","went background");
                    for(Listener listener:listeners){
                        try {
                            listener.onBecameBackground();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("lpftag","Listener threw exception:"+e.toString());
                        }
                    }
                }else{
                    Log.d("lpftag","still foreground");
                }
            }
        },CHECK_DELAY);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() called with: activity = [" + activity + "], savedInstanceState = [" + savedInstanceState + "]");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
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
}
