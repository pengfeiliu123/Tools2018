package com.lpf.tools.feature.theme.util;

import com.lpf.tools.App;

/**
 * Created by liupengfei on 2018/12/14 12:29.
 */
public class ResourcesUtil {

    public static int getColor(int resourceId){
        return App.applicationContext().getResources().getColor(resourceId);
    }
}
