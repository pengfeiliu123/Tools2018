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

    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;

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
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            // connect error
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedException) {
            // timeout error
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            // parse error
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                onError(toString());
            } else {
                onError("未知错误");
            }
        }
    }

    private void onException(int unKnownError) {
        switch (unKnownError) {
            case CONNECT_ERROR:
                onError("连接错误");
                break;

            case CONNECT_TIMEOUT:
                onError("连接超时");
                break;

            case BAD_NETWORK:
                onError("网络问题");
                break;

            case PARSE_ERROR:
                onError("解析数据失败");
                break;

            default:
                break;
        }
    }

    public abstract void onSuccess(T o);

    public abstract void onError(String msg);
}
