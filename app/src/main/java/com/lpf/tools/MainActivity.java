package com.lpf.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.entity.FlowTag;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;
import com.lpf.tools.feature.navigation.NavigationActivity;
import com.lpf.tools.feature.permission.PermissionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.flow_layout)
    TagFlowLayout flowLayout;

    private List<FlowTag> tagDatas;

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
        tagDatas.add(new FlowTag("permission", PermissionActivity.class));
        tagDatas.add(new FlowTag("navigation", NavigationActivity.class));
    }

    private void initFlowLayout() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        flowLayout.setAdapter(new TagAdapter<FlowTag>(tagDatas) {
            @Override
            public View getView(FlowLayout parent, int position, FlowTag tagBean) {
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
