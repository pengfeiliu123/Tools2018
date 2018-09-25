package com.lpf.tools.feature.collapsingToolbar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.tools.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {


    private TextView testName;
    private String testNameStr;

    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment newInstance(String testName) {
        TestFragment testFragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString("testName", testName);
        testFragment.setArguments(args);
        return testFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testNameStr = getArguments().getString("testName");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_test,container,false);
        testName = rootView.findViewById(R.id.test_title);
        testName.setText(testNameStr);
        return rootView;
    }

}
