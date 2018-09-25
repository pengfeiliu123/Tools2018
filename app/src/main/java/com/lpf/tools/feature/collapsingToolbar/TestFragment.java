package com.lpf.tools.feature.collapsingToolbar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.feature.widgets.recyclerviewdemo.MXRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private MXRecyclerView mxRecyclerView;
    private MultiTypeAdapter adapter;
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
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);
        mxRecyclerView = rootView.findViewById(R.id.test_recycler_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mockDatas.add("Test List Item " + i);
        }

        adapter = new MultiTypeAdapter(mockDatas);
        adapter.register(String.class, new TestCardBinder());

        mxRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mxRecyclerView.addItemDecoration(dividerItemDecoration);
        mxRecyclerView.setAdapter(adapter);

//        adapter.setItems(mockDatas);
        adapter.notifyDataSetChanged();
    }
}
