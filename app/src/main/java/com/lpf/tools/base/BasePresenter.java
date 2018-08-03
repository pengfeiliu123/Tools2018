package com.lpf.tools.base;

import com.lpf.tools.api.ApiRetrofit;
import com.lpf.tools.api.IApiService;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liupengfei on 2018/8/1 15:20.
 */
public class BasePresenter<V extends BaseView> {

    private CompositeDisposable compositeDisposable;

    protected V baseView;

    protected IApiService apiService = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));
    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
