package com.lpf.tools.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by liupengfei on 2018/8/1 12:54.
 */
public class BaseModel<T> implements Serializable {

    private int resultCode;
    private String errMsg;
    private T result;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
