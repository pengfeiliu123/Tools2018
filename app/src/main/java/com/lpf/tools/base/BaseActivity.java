package com.lpf.tools.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by liupengfei on 2018/8/1 15:32.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected Context context;
    protected Toast toast;
    protected P presenter;

    protected abstract P createPresenter();
    protected abstract int getLayoutId();
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        presenter = createPresenter();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected void initData() {

    }

    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }
}
