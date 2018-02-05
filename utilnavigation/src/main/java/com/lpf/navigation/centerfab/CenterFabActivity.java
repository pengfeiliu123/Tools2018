package com.lpf.navigation.centerfab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.lpf.navigation.BaseFragment;
import com.lpf.navigation.BottomNavigationViewEx;
import com.lpf.navigation.R;

import java.util.ArrayList;
import java.util.List;

public class CenterFabActivity extends AppCompatActivity {
    private static final String TAG = CenterFabActivity.class.getSimpleName();
    private VpAdapter adapter;

    // collections
    private List<Fragment> fragments;// used for ViewPager adapter
    private BottomNavigationViewEx bnve;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_fab);

        bnve = (BottomNavigationViewEx)findViewById(R.id.bnve);
        vp = (ViewPager)findViewById(R.id.vp);
        initData();
        initView();
        initEvent();
    }


    /**
     * create fragments
     */
    private void initData() {
        fragments = new ArrayList<>(4);

        // create music fragment and add it
        BaseFragment musicFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.music));
        musicFragment.setArguments(bundle);

        // create backup fragment and add it
        BaseFragment backupFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        backupFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment favorFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.favor));
        favorFragment.setArguments(bundle);

        // create friends fragment and add it
        BaseFragment visibilityFragment = new BaseFragment();
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.visibility));
        visibilityFragment.setArguments(bundle);


        // add to fragments for adapter
        fragments.add(musicFragment);
        fragments.add(backupFragment);
        fragments.add(favorFragment);
        fragments.add(visibilityFragment);
    }


    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {
        bnve.enableItemShiftingMode(false);
        bnve.enableShiftingMode(false);
        bnve.enableAnimation(false);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                int i = item.getItemId();
                if (i == R.id.i_music) {
                    position = 0;

                } else if (i == R.id.i_backup) {
                    position = 1;

                } else if (i == R.id.i_favor) {
                    position = 2;

                } else if (i == R.id.i_visibility) {
                    position = 3;

                } else if (i == R.id.i_empty) {
                    return false;
                }
                if(previousPosition != position) {
                  vp.setCurrentItem(position, false);
                  previousPosition = position;
                    Log.i(TAG, "-----bnve-------- previous item:" + bnve.getCurrentItem() + " current item:" + position + " ------------------");
                }

                return true;
            }
        });

        // set listener to change the current checked item of bottom nav when scroll view pager
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "-----ViewPager-------- previous item:" + bnve.getCurrentItem() + " current item:" + position + " ------------------");
                if (position >= 2)// 2 is center
                    position++;// if page is 2, need set bottom item to 3, and the same to 3 -> 4
                bnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // center item click listener
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(CenterFabActivity.this, "Center", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}
