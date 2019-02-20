package me.drakeet.multitype.ext;

import android.view.LayoutInflater;

/**
 * Created by liupengfei on 2019/2/19 14:23.
 */
public interface MultiTypeViewCacheProvider {
    ViewCache getMultiTypeViewCache();
    LayoutInflater getMultiTypeLayoutInflater();
    void saveLayoutInflater(LayoutInflater inflater);
}
