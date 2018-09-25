package com.lpf.tools.feature.collapsingToolbar;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 2018/9/25 16:17.
 */
public class SimplePagerAdapter extends FragmentPagerAdapter {

    private List<String> dataList;
    private FragmentManager fm;

    public SimplePagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        dataList = new ArrayList<>();
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public List<String> getDataList(){
        return dataList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Fragment getItem(int position) {
        String testName = dataList.get(position);
        return TestFragment.newInstance(testName);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return FragmentPagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
}
