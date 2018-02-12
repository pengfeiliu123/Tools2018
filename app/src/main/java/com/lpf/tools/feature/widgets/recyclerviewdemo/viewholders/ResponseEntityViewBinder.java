package com.lpf.tools.feature.widgets.recyclerviewdemo.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.feature.networkdemo.ResponseEntity;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by liupengfei on 2018/2/11 17:31.
 */
public class ResponseEntityViewBinder extends ItemViewBinder<ResponseEntity.SubjectsBean, ResponseEntityViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_response_entity, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ResponseEntity.SubjectsBean bean) {
        holder.movieName.setText(bean.getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;

        ViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
        }
    }
}
