package com.lpf.tools.base;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by liupengfei on 2018/8/1 14:05.
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    protected BaseView view;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        try {
            BaseModel model = (BaseModel) t;
            if (model.getResultCode() == 0) {
                onSuccess(t);
            } else {
                if (view != null) {
                    // show error code
                    view.onErrorCode(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.toString());
        }
    }

    @Override
    public void onComplete() {
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideLoading();
        }

        if (e instanceof HttpException) {
            // http error
            onError(((HttpException) e).code()+ "网络问题");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            // connect error
            onError(((HttpException) e).code()+ "连接错误");
        } else if (e instanceof InterruptedException) {
            // timeout error
            onError(((HttpException) e).code()+ "连接超时");
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            // parse error
            onError(((HttpException) e).code()+ "解析数据失败");
        } else {
            if (e != null) {
                onError(toString());
            } else {
                onError(((HttpException) e).code()+ "未知错误");
            }
        }
    }

    public abstract void onSuccess(T o);

    public abstract void onError(String msg);
}
