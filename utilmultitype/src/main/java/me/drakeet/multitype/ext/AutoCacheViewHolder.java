package me.drakeet.multitype.ext;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liupengfei on 2019/2/19 14:16.
 */
public abstract class AutoCacheViewHolder extends RecyclerView.ViewHolder{
    public AutoCacheViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract RecyclerView getRecyclerView();
}
