package com.lpf.tools.feature.collapsingToolbar;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.lpf.tools.R;
import com.lpf.utilindicator.ext.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollapsingToolActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    private MagicIndicator magicIndicator;
    private CommonNavigator commonNavigator;
    private NavigatorAdapter navigatorAdapter;

    private ViewPager viewPager;
    private SimplePagerAdapter simplePagerAdapter;

    private List<String> mockDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool);

        mockDatas();
        initViews();
    }

    private void mockDatas() {
        for (int i = 0; i < 3; i++) {
            mockDatas.add("Page Name " + i);
        }
    }

    private void initViews() {

        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        magicIndicator = findViewById(R.id.magic_indicator);

        toolbar.setTitle("CustomTitle");

        initViewPager();
        initMagicIndicator();

    }

    private void initViewPager() {
        simplePagerAdapter = new SimplePagerAdapter(getSupportFragmentManager());
        if (mockDatas.size() > 0) {
            simplePagerAdapter.setDataList(mockDatas);
        } else {
            simplePagerAdapter.setDataList(Collections.<String>emptyList());
        }
        viewPager.setAdapter(simplePagerAdapter);
    }

    private void initMagicIndicator() {
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        navigatorAdapter = new NavigatorAdapter();
        commonNavigator.setAdapter(navigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private class NavigatorAdapter extends CommonNavigatorAdapter {
        @Override
        public int getCount() {
            return simplePagerAdapter.getCount();
        }

        @Override
        public IPagerTitleView getTitleView(Context context, final int i) {
            SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
            simplePagerTitleView.setText(simplePagerAdapter.getDataList().get(i));
            TypedValue outValue = new TypedValue();
            getResources().getValue(R.dimen.view_pager_tab_text_size, outValue, true);
            simplePagerTitleView.setTextSize(outValue.getFloat());
            simplePagerTitleView.setNormalColor(getResources().getColor(R.color.magic_indicator_title_unselected_color));
            simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.magic_indicator_title_selected_color));
            simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(i);
                }
            });
            return simplePagerTitleView;
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            LinePagerIndicator indicator = new LinePagerIndicator(context);
            indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
            indicator.setLineHeight(UIUtil.dip2px(context, 2));
            indicator.setLineWidth(UIUtil.dip2px(context, 50));
            indicator.setRoundRadius(UIUtil.dip2px(context, 0));
            indicator.setStartInterpolator(new AccelerateInterpolator());
            indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
            indicator.setColors(getResources().getColor(R.color.magic_indicator_line_selected_color));
            return indicator;
        }
    }
}
