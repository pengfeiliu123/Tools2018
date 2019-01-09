package com.lpf.tools.feature.theme.util;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.text.TextUtils;

import com.lpf.tools.R;
import com.lpf.utilcode.util.PreferencesUtil;

import java.util.HashMap;
import java.util.Map;

public class SkinManager {

    private @AppTheme
    String theme;

    private SkinManager(){
        theme = PreferencesUtil.getAppTheme();
    }

    public static SkinManager get() {
        return SkinManagerHolder.instance;
    }

    private static class SkinManagerHolder {
        private static SkinManager instance = new SkinManager();
    }

    public void setDarkTheme() {
        setOnlineTheme(AppTheme.THEME_DARK);
    }

    public void setLightTheme(){
        setOnlineTheme(AppTheme.THEME_DEFAULT);
    }

    public void setOnlineTheme(@AppTheme String newTheme) {
        PreferencesUtil.setAppTheme(newTheme);
        theme = newTheme;
    }

    public boolean isDarkTheme() {
        return TextUtils.equals(theme, AppTheme.THEME_DARK);
    }

    private ISkinStrategy getCurrentStrategy(){
        if(isDarkTheme()){
            return ThemeDarkStrategy.getInstance();
        }else{
            return ThemeLightStrategy.getInstance();
        }
    }

    public static ISkinStrategy getSkinStrategy(){
        return get().getCurrentStrategy();
    }

    public static String getResourceNameForSkin(Context context, int id) {
        String name = context.getResources().getResourceEntryName(id);
        String newName = getSkinStrategy().getResourceName(name);
        if (TextUtils.equals(name, newName))
            return null;

        return newName;
    }


    // switch theme from default and dark.
    public boolean switchTheme() {
        if (isDarkTheme()) {
            setOnlineTheme(AppTheme.THEME_DEFAULT);
        } else {
            setOnlineTheme(AppTheme.THEME_DARK);
        }
        return true;
    }

    public @AppTheme
    String getTheme() {
        return theme;
    }

    public @StyleRes
    int getThemeId(@ThemeStyles String themeStyle) {
        if (TextUtils.isEmpty(getTheme())) {
            return defaultTheme.get(themeStyle);
        }
        switch (getTheme()) {
            case AppTheme.THEME_DARK:
                return darkTheme.get(themeStyle);
            default:
                return defaultTheme.get(themeStyle);
        }
    }

    private static Map<String, Integer> darkTheme = new HashMap<String, Integer>(){
        {
            put(ThemeStyles.MAIN_ACTIVITY_THEME, R.style.MainActivityDarkTheme);
            put(ThemeStyles.SKIN_TEST_ACTIVITY_THEME, R.style.SkinTestActivityDarkTheme);
        }
    };

    private static Map<String, Integer> defaultTheme = new HashMap<String, Integer>(){
        {
            put(ThemeStyles.MAIN_ACTIVITY_THEME, R.style.MainActivityLightTheme);
            put(ThemeStyles.SKIN_TEST_ACTIVITY_THEME, R.style.SkinTestActivityLightTheme);
        }
    };
}
