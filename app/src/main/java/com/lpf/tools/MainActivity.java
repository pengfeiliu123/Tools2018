package com.lpf.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.entity.TagFlowEntity;
import com.lpf.tools.feature.banner.BannerActivity;
import com.lpf.tools.feature.dbdemo.DbActivity;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;
import com.lpf.tools.feature.magicIndicator.IndicatorActivity;
import com.lpf.tools.feature.navigation.NavigationActivity;
import com.lpf.tools.feature.networkdemo.NetworkActivity;
import com.lpf.tools.feature.permission.PermissionActivity;
import com.lpf.tools.feature.widgets.recyclerviewdemo.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.flow_layout)
    TagFlowLayout flowLayout;

    private List<TagFlowEntity> tagDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTagDatas();
        initFlowLayout();
    }

    private void initTagDatas() {
        tagDatas = new ArrayList<>();
        tagDatas.add(new TagFlowEntity("permission", PermissionActivity.class));
        tagDatas.add(new TagFlowEntity("navigation", NavigationActivity.class));
        tagDatas.add(new TagFlowEntity("indicator", IndicatorActivity.class));
        tagDatas.add(new TagFlowEntity("banner", BannerActivity.class));
        tagDatas.add(new TagFlowEntity("network", NetworkActivity.class));
        tagDatas.add(new TagFlowEntity("widgets", RecyclerViewActivity.class));
        tagDatas.add(new TagFlowEntity("db", DbActivity.class));
    }

    private void initFlowLayout() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        flowLayout.setAdapter(new TagAdapter<TagFlowEntity>(tagDatas) {
            @Override
            public View getView(FlowLayout parent, int position, TagFlowEntity tagBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_tv, flowLayout, false);
                tv.setText(tagBean.tagName);
                return tv;
            }
        });

        flowLayout.setMaxSelectCount(1);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                startActivity(tagDatas.get(position).tagClass);
                return true;
            }
        });
    }

    private void startActivity(Class tagClass) {
        Intent intent = new Intent(this, tagClass);
        startActivity(intent);
    }
}
