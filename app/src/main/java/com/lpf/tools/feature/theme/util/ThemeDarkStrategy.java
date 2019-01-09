package com.lpf.tools.feature.theme.util;

import android.view.View;

/**
 * Created by liupengfei on 2018/12/12 12:49.
 */
public class ThemeDarkStrategy extends ThemeDarkStrategyBase {

    private ThemeDarkStrategy() {
    }

    public static final ThemeDarkStrategy getInstance() {
        return ThemeDarkStrategySingleton.strategy;
    }

    private static class ThemeDarkStrategySingleton {
        private static ThemeDarkStrategy strategy = new ThemeDarkStrategy();
    }

    @Override
    public void setMainActivityBgColor(View view) {

    }

    @Override
    protected String getSuffix() {
        return DARK;
    }
}
