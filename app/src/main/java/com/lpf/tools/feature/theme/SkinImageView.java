package com.lpf.tools.feature.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lpf.tools.R;

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
        updateDrawableForTheme(context, attrs, 0);
    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        updateDrawableForTheme(context, attrs, defStyleAttr);
    }

    private void updateDrawableForTheme(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.SkinImageView, defStyleAttr, 0);

        try {
            int id = a.getResourceId(R.styleable.SkinImageView_android_src, 0);
            if (id == 0)
                return;

            String name = context.getResources().getResourceEntryName(id);
            String newName = SkinManager.getSkinStrategy().getResourceName(name);
            if (TextUtils.isEmpty(newName))
                return;

            int drawableId = context.getResources().getIdentifier(newName, "drawable", context.getPackageName());
            if (drawableId == 0)
                return;

            Drawable drawable = context.getResources().getDrawable(drawableId);
            setImageDrawable(drawable);
        } finally {
            a.recycle();
        }
    }
}
