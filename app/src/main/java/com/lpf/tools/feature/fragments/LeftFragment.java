package com.lpf.tools.feature.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lpf.tools.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends ListFragment {


    OnLeftSelectedListener onLeftSelectedListener;

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onLeftSelectedListener = (OnLeftSelectedListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement OnLeftSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_left_list_column,R.id.text);
        setListAdapter(adapter);
        adapter.addAll(createTestListData(20));
    }

    private List<String> createTestListData(int count) {
        List<String> list = new ArrayList<String>();
        for (int j = 0; j < count; j++) {
            list.add("count="+j);
        }
        return list;
    }

    public interface OnLeftSelectedListener{
        void onLeftSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
        onLeftSelectedListener.onLeftSelected(position);
    }
}
