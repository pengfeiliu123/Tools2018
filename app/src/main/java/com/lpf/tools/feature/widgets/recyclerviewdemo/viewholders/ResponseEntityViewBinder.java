package com.lpf.tools.feature.widgets.recyclerviewdemo.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpf.tools.R;
import com.lpf.tools.feature.networkdemo.ResponseEntity;
import com.lpf.tools.imageloader.ImageLoader;

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
        holder.bindData(bean, holder.getAdapterPosition());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        ImageView movieImage;

        ViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieName = itemView.findViewById(R.id.movieName);
        }

        void bindData(final ResponseEntity.SubjectsBean bean, final int position) {
            if (bean == null) {
                return;
            }
            movieName.setText(bean.getTitle());
            ImageLoader.getInstance().loadCircleImage(itemView.getContext(), bean.getImages().getMedium(), movieImage, R.mipmap.ic_launcher);
        }
    }
}
