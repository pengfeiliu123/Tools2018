package com.lpf.tools.feature.networkdemo;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.base.BaseActivity;
import com.lpf.tools.views.LoadingDialog;
import com.lpf.utilcode.receiver.NetworkMessage;
import com.lpf.utilcode.receiver.NetworkStateReceiver;
import com.lpf.utilcode.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class NetworkActivity extends BaseActivity<INetworkPresenter> implements INetworkView {

    @BindView(R.id.response_text)
    TextView responseText;

    // add network receiver
    NetworkStateReceiver netWorkStateReceiver;

    //保存外部activity的弱引用
    private WeakReference<Context> weakReference;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected INetworkPresenter createPresenter() {
        return new INetworkPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initView() {
        weakReference = new WeakReference<>(context);
        loadingDialog = new LoadingDialog();
    }

    @Override
    protected void initData() {
        presenter.doGetDouBanList(0, 10);
    }

    @Override
    public void onGetDouBanSuccess(ResponseEntity response) {
        responseText.setText(response.toString());
    }

    @Override
    public void showLoading() {
        NetworkActivity activity = (NetworkActivity) weakReference.get();
        if (activity != null) {
            loadingDialog.show(activity.getSupportFragmentManager(), "LOADING");
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetworkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetworkMessage event) {
        ToastUtil.showShort(this, event.name + "-->request data");
    }
}
