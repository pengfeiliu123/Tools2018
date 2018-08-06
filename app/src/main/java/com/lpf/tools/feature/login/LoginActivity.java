package com.lpf.tools.feature.login;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.base.BaseActivity;
import com.lpf.tools.base.BaseModel;
import com.lpf.tools.feature.networkdemo.ResponseEntity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.email_sign_in_button)
    Button btnSignIn;
    @BindView(R.id.login_tip)
    TextView loginTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                Log.d("lpf", "id->" + id);
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onLoginSuccess(ResponseEntity response) {
        showToast("login success"+response.getTitle());
        hideLoading();
    }

    @Override
    public void showLoading() {
        loginTip.setText("login...");
    }

    @Override
    public void hideLoading() {
        loginTip.setVisibility(View.GONE);
    }

    @Override
    public void onErrorCode(BaseModel model) {
        loginTip.setVisibility(View.GONE);
        showToast("login failed");
    }

    @OnClick(R.id.email_sign_in_button)
    public void onViewClicked() {
        presenter.doLogin(email.getText().toString(), etPassword.getText().toString());
    }
}
