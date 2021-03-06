package com.lpf.tools.feature.theme.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lpf.tools.App;
import com.lpf.tools.feature.theme.util.SkinManager;

/**
 * @Deprecated replace it with AppCompatImageView and set app:mxSkin="src"
 */
@Deprecated
public class SkinImageView extends AppCompatImageView {

    public SkinImageView(Context context) {
        super(context);
    }

    public SkinImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 可以动态修改图片
     * @param resId
     */
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if(SkinManager.get().isDarkTheme()){
            String name = App.getsInstance().getResources().getResourceEntryName(resId);
            String newName = SkinManager.getSkinStrategy().getResourceName(name);
            if (TextUtils.isEmpty(newName))
                return;

            int drawableId =  App.getsInstance().getResources().getIdentifier(newName, "drawable",  App.getsInstance().getPackageName());
            if (drawableId == 0)
                return;

            Drawable drawable =  App.getsInstance().getResources().getDrawable(drawableId);
            setImageDrawable(drawable);
        }else{
            setImageDrawable(App.getsInstance().getResources().getDrawable(resId));
        }
    }
}
