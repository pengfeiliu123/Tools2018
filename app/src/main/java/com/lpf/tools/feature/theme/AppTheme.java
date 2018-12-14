package com.lpf.tools.feature.theme;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        AppTheme.THEME_DEFAULT,
        AppTheme.THEME_DARK
})
@Retention(RetentionPolicy.SOURCE)
public @interface AppTheme {
    String THEME_DEFAULT = "";
    String THEME_DARK = "theme_dark";
}
