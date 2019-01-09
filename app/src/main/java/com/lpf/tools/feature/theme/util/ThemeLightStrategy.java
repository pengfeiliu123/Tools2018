package com.lpf.tools.feature.theme.util;

import android.view.View;

/**
 * Created by liupengfei on 2018/12/12 12:50.
 */
public class ThemeLightStrategy  extends ThemeDarkStrategyBase {

    private ThemeLightStrategy() {
    }

    public static final ThemeLightStrategy getInstance() {
        return ThemeLightStrategySingleton.strategy;
    }

    private static class ThemeLightStrategySingleton {
        private static ThemeLightStrategy strategy = new ThemeLightStrategy();
    }

    @Override
    public void setMainActivityBgColor(View view) {

    }

    @Override
    protected String getSuffix() {
        return LIGHT;
    }
}
