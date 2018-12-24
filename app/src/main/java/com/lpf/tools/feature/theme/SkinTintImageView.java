package com.lpf.tools.feature.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lpf.tools.R;
import com.lpf.tools.utilskin.ThemeResourceUtil;

/**
 * deprecated, replace it with AppCompatImageView, and set app:mxSkin="tint"
 */
@Deprecated
public class SkinTintImageView extends AppCompatImageView {
    public SkinTintImageView(Context context) {
        super(context);
    }

    public SkinTintImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTint(context, attrs, 0);
    }

    public SkinTintImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        updateTint(context, attrs, defStyleAttr);
    }

    private void updateTint(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.SkinTintImageView, defStyleAttr, 0);

        try {
            int id = a.getResourceId(R.styleable.SkinTintImageView_android_tint, 0);
            if (id == 0)
                return;

            String newName = SkinManager.getResourceNameForSkin(context, id);
            if (TextUtils.isEmpty(newName))
                return;

            int colorId = ThemeResourceUtil.getColorId(context, newName);
            if (colorId == 0)
                return;

            ColorStateList colorStateList = AppCompatResources.getColorStateList(context, colorId);

            ImageViewCompat.setImageTintList(this, colorStateList);
        } finally {
            a.recycle();
        }
    }
}
