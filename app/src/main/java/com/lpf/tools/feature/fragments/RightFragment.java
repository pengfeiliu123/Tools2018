package com.lpf.tools.feature.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lpf.tools.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends ListFragment {


    private List<String> datas;
    ArrayAdapter<String> adapter;

    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateListData();
    }

    private List<String> createTestListData() {
        datas.clear();
        for (int j = 0; j < 10; j++) {
            datas.add("right=" + Math.random() * 10);
        }
        return datas;
    }

    public void updateListData() {
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_left_list_column, R.id.text);
        datas = new ArrayList<String>(10);
        setListAdapter(adapter);
        datas = createTestListData();
        adapter.addAll(datas);
    }
}
