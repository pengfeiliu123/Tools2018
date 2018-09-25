package com.lpf.tools.feature.collapsingToolbar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.tools.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by liupengfei on 2018/9/25 17:31.
 */
public class TestCardBinder extends ItemViewBinder<String, TestCardBinder.InnerViewHolder> {
    @NonNull
    @Override
    protected InnerViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new InnerViewHolder(inflater.inflate(R.layout.item_test_rv,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull InnerViewHolder holder, @NonNull String item) {
        holder.bindData(item);
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;


        public InnerViewHolder(View itemView) {
            super(itemView);
        }

        public void bindData(String item) {

        }
    }
}
