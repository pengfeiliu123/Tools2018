package com.lpf.tools.feature.theme.customview;

import android.content.Context;
import android.support.annotation.AnyRes;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lpf.tools.App;
import com.lpf.tools.feature.theme.util.SkinManager;
import com.lpf.tools.utilskin.ThemeResourceUtil;

/**
 * deprecated, replace it with AppCompatTextView, and set app:mxSkin="textColor"
 */
@Deprecated
public class SkinTextView extends android.support.v7.widget.AppCompatTextView {

    public SkinTextView(Context context) {
        super(context);
    }

    public SkinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCustomTextColor(@AnyRes int resourceId) {
        if(SkinManager.get().isDarkTheme()){
            String name = App.getsInstance().getResources().getResourceEntryName(resourceId);
            String newName = SkinManager.getSkinStrategy().getResourceName(name);
            if (TextUtils.isEmpty(newName))
                return;

            int colorId = ThemeResourceUtil.getColorId(App.applicationContext(), newName);
            if (colorId == 0)
                return;

            setTextColor(getResources().getColor(colorId));
        }else{
            setTextColor(getResources().getColor(resourceId));
        }
    }
}
