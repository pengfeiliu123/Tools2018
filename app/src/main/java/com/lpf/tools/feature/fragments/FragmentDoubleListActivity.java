package com.lpf.tools.feature.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.lpf.tools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDoubleListActivity extends AppCompatActivity implements LeftFragment.OnLeftSelectedListener {


    LeftFragment leftFragment;
    RightFragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_double_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        leftFragment = (LeftFragment) fragmentManager.findFragmentById(R.id.left);
        rightFragment = (RightFragment) fragmentManager.findFragmentById(R.id.right);
    }

    @Override
    public void onLeftSelected(int position) {
        rightFragment.updateListData();
    }
}
