package com.lpf.utilcode.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtil {

    private static PreferencesUtil instance;

    private static SharedPreferences sharedPreferences;
    public static final String USER_NAME = "user_name";
    public static final String APP_THEME = "app_theme";

    private PreferencesUtil(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferencesUtil getInstance(Context context) {
        if (instance == null)
            instance = new PreferencesUtil(context);
        return instance;
    }

    public String getTestName() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(USER_NAME, "");
        }
        return "";
    }

    public boolean saveTestName(String name) {
        if (sharedPreferences != null) {
            return sharedPreferences.edit().putString(USER_NAME, name)
                    .commit();
        }
        return false;
    }

    public static String getAppTheme() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(APP_THEME, "");
        }
        return "";
    }

    public static void setAppTheme(String name) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(APP_THEME, name).commit();
        }
    }

}
