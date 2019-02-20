package me.drakeet.multitype.ext;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by liupengfei on 2019/2/19 14:24.
 */
public interface ViewCache {
    View getViewById(int layoutId, Class tag);

    boolean recycle(RecyclerView recyclerView);

    boolean recycle(MultiTypeAdapter adapter);
}
