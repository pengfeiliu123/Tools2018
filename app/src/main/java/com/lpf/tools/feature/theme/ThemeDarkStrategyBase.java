package com.lpf.tools.feature.theme;

public abstract class ThemeDarkStrategyBase implements ISkinStrategy {

    public static final String LIGHT = "light";
    public static final String DARK = "dark";

    public static final String SUFFIX_FLAG = "__";


    @Override
    public String getResourceName(String name) {
        int index = name.lastIndexOf(SUFFIX_FLAG);
        if (index == -1) {
            return null;
        }

        String pureName = name.substring(0, index);

        return pureName + SUFFIX_FLAG + getSuffix();
    }

    protected abstract String getSuffix();
}
