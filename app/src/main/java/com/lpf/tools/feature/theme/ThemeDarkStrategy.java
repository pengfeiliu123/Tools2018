package com.lpf.tools.feature.theme;

import android.view.View;

import com.lpf.tools.App;
import com.lpf.tools.R;

/**
 * Created by liupengfei on 2018/12/12 12:49.
 */
public class ThemeDarkStrategy implements ISkinStrategy {

    private ThemeDarkStrategy() {
    }

    public static final ThemeDarkStrategy getInstance() {
        return ThemeDarkStrategySingleton.strategy;
    }

    private static class ThemeDarkStrategySingleton {
        private static ThemeDarkStrategy strategy = new ThemeDarkStrategy();
    }

    //***************** set specific theme view **********************

    @Override
    public void setMainActivityBgColor(View view) {
        view.setBackgroundColor(ResourcesUtil.getColor(R.color.homeBackgroundColorDark));
    }
}
