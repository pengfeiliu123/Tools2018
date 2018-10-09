package com.lpf.tools.feature.magicIndicator;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.tools.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragmentLifeCycle extends AppCompatActivity {


    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private List<String> tabIndicators;
    private List<Integer> tabImages;
    private List<Fragment> tabFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment_life_cycle);
        ButterKnife.bind(this);

        initToolBar();
        initContent();
        initTab();
    }

    private void initToolBar() {
        toolBar.setTitle("TabLayout");
        toolBar.setTitleTextColor(getResources().getColor(R.color.colorGreen));
    }

    private void initTab() {
        tlTab.setTabMode(TabLayout.MODE_FIXED);
        tlTab.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(tlTab, 10);
        tlTab.setupWithViewPager(vpContent);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = tlTab.getTabAt(i);
            if (itemTab != null) {
//                itemTab.setCustomView(R.layout.item_tab_layout_custom);
//                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.itemTextView);
//                ImageView itemIv = (ImageView) itemTab.getCustomView().findViewById(R.id.itemImageView);
//                itemTv.setText(tabIndicators.get(i));
//                itemIv.setImageResource(tabImages.get(i));

                TextView tv = new TextView(this);
                tv.setText(tabIndicators.get(i));
                itemTab.setCustomView(tv);
            }
        }
        tlTab.getTabAt(0).getCustomView().setSelected(true);

    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("消息");
        tabIndicators.add("任务");
        tabIndicators.add("团队");
        tabIndicators.add("部门");
        tabImages = new ArrayList<>();
        tabImages.add(R.drawable.ic_face_smile);
        tabImages.add(R.drawable.ic_face_smile);
        tabImages.add(R.drawable.ic_face_smile);
        tabImages.add(R.drawable.ic_face_smile);
        tabFragments = new ArrayList<>();
        for (String s : tabIndicators) {
            tabFragments.add(TabContentFragment.newInstance(s));
        }
        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(contentAdapter);
    }

    private class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
