package com.lpf.tools.feature.networkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lpf.tools.R;
import com.lpf.tools.network.RequestMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NetworkActivity extends AppCompatActivity {

    @BindView(R.id.response_text)
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);

        initRequestData();
    }

    private void initRequestData() {
        //request data.
        RequestMethod.getInstance().getResponseData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        onResponseData(responseEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    private void onResponseData(ResponseEntity responseEntity) {
        responseText.setText(responseEntity.toString());
    }
}
