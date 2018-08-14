package com.lpf.tools.feature.dbdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.entity.TagFlowEntity;
import com.lpf.tools.feature.BaseFeatureActivity;
import com.lpf.tools.feature.dbdemo.litepal.LitepalDbActivity;
import com.lpf.tools.feature.dbdemo.room.ArticleActivity;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;
import com.lpf.utilbanner.BannerDemoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DbActivity extends BaseFeatureActivity {

    @BindView(R.id.db_flow_layout)
    TagFlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_db;
    }

    @Override
    public void initTagDatas() {
        tagDatas = new ArrayList<>();
        tagDatas.add(new TagFlowEntity("litepal", LitepalDbActivity.class));
        tagDatas.add(new TagFlowEntity("room", ArticleActivity.class));
    }

    @Override
    public void initLayoutView() {
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
