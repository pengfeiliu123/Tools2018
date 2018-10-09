package com.lpf.tools.feature.magicIndicator;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.tools.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TabContentFragment extends Fragment {


    private String text;

    public TabContentFragment(String text) {
        this.text = text;
    }

    public static Fragment newInstance(String s) {
        return new TabContentFragment(s);
    }

    @Override
    public void onAttach(Context context) {
        Log.e("TYH", text + "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("TYH", text + "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TYH", text + "onCreateView");
        TextView textView = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        return textView;
    }

    @Override
    public void onStart() {
        Log.e("TYH", text + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("TYH", text + "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e("TYH", text + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e("TYH", text + "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e("TYH", text + "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e("TYH", text + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e("TYH", text + "onDetach");
        super.onDetach();
    }

}
