package com.lpf.tools.feature.theme;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.lpf.tools.feature.theme.ThemeStyles.MAIN_ACTIVITY_THEME;
import static com.lpf.tools.feature.theme.ThemeStyles.SKIN_TEST_ACTIVITY_THEME;

@StringDef({
        MAIN_ACTIVITY_THEME,
        SKIN_TEST_ACTIVITY_THEME
})
@Retention(RetentionPolicy.SOURCE)
public @interface ThemeStyles {
    String MAIN_ACTIVITY_THEME = "main_activity_theme";
    String SKIN_TEST_ACTIVITY_THEME = "skin_test_activity_theme";
}
