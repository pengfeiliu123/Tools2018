package com.lpf.tools.feature.theme;

import android.view.View;

import com.lpf.tools.App;
import com.lpf.tools.R;

/**
 * Created by liupengfei on 2018/12/12 12:50.
 */
public class ThemeLightStrategy implements ISkinStrategy {

    private ThemeLightStrategy() {
    }

    public static final ThemeLightStrategy getInstance() {
        return ThemeLightStrategySingleton.strategy;
    }

    private static class ThemeLightStrategySingleton {
        private static ThemeLightStrategy strategy = new ThemeLightStrategy();
    }

    //***************** set specific theme view **********************


    @Override
    public void setMainActivityBgColor(View view) {
        view.setBackgroundColor(ResourcesUtil.getColor(R.color.homeBackgroundColorLight));
    }
}
