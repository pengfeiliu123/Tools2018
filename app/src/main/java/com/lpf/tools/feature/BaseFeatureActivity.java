package com.lpf.tools.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lpf.tools.entity.TagFlowEntity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by liupengfei on 2018/2/6 11:43.
 */

public abstract class BaseFeatureActivity extends AppCompatActivity {

    protected List<TagFlowEntity> tagDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initTagDatas();
        initLayoutView();
    }

    public abstract int getLayout();

    public abstract void initTagDatas();

    public abstract void initLayoutView();
}
