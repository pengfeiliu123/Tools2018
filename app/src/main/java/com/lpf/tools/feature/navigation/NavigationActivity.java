package com.lpf.tools.feature.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.navigation.badgeview.BadgeViewActivity;
import com.lpf.navigation.centerfab.CenterFabActivity;
import com.lpf.navigation.setupwithviewpager.SetupWithViewPagerActivity;
import com.lpf.navigation.style.StyleActivity;
import com.lpf.navigation.viewpager.WithViewPagerActivity;
import com.lpf.tools.R;
import com.lpf.tools.entity.TagFlowEntity;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {

    @BindView(R.id.navi_flow_layout)
    TagFlowLayout flowLayout;

    private List<TagFlowEntity> tagDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        initTagDatas();
        initFlowLayout();
    }

    private void initTagDatas() {
        tagDatas = new ArrayList<>();
        tagDatas.add(new TagFlowEntity("bottom_nav_styles",StyleActivity.class));
        tagDatas.add(new TagFlowEntity("bottom_nav_setupWithViewPager",SetupWithViewPagerActivity.class));
        tagDatas.add(new TagFlowEntity("bottom_nav_badgeView",BadgeViewActivity.class));
        tagDatas.add(new TagFlowEntity("bottom_nav_withViewPager",WithViewPagerActivity.class));
        tagDatas.add(new TagFlowEntity("bottom_nav_centerFab",CenterFabActivity.class));

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
