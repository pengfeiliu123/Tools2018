package com.lpf.tools.feature.theme.util;

import android.view.View;

/**
 * Created by liupengfei on 2018/12/12 12:03.
 */
public interface ISkinStrategy {

    void setMainActivityBgColor(View view);

    String getResourceName(String name);
}
