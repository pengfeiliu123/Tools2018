package com.lpf.tools.feature.magicIndicator;

import android.content.Intent;
import android.os.Bundle;
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
import com.lpf.tools.feature.BaseFeatureActivity;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;
import com.lpf.utilindicator.styles.BadgeTabExampleActivity;
import com.lpf.utilindicator.styles.CustomNavigatorExampleActivity;
import com.lpf.utilindicator.styles.DynamicTabExampleActivity;
import com.lpf.utilindicator.styles.FixedTabExampleActivity;
import com.lpf.utilindicator.styles.FragmentContainerExampleActivity;
import com.lpf.utilindicator.styles.LoadCustomLayoutExampleActivity;
import com.lpf.utilindicator.styles.NoTabOnlyIndicatorExampleActivity;
import com.lpf.utilindicator.styles.ScrollableTabExampleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndicatorActivity extends BaseFeatureActivity {

    @BindView(R.id.indicator_tag_flow)
    TagFlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_indicator;
    }

    @Override
    public void initTagDatas() {
        tagDatas = new ArrayList<>();
        tagDatas.add(new TagFlowEntity("scrollable_tab", ScrollableTabExampleActivity.class));
        tagDatas.add(new TagFlowEntity("fixed_tab", FixedTabExampleActivity.class));
        tagDatas.add(new TagFlowEntity("dynamic_tab", DynamicTabExampleActivity.class));
        tagDatas.add(new TagFlowEntity("no_tab_only_indicator", NoTabOnlyIndicatorExampleActivity.class));
        tagDatas.add(new TagFlowEntity("tab_with_badge_view", BadgeTabExampleActivity.class));
        tagDatas.add(new TagFlowEntity("work_with_fragment_container", FragmentContainerExampleActivity.class));
        tagDatas.add(new TagFlowEntity("load_custom_layout", LoadCustomLayoutExampleActivity.class));
        tagDatas.add(new TagFlowEntity("custom_navigator", CustomNavigatorExampleActivity.class));
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
