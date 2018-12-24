package com.lpf.tools.utilskin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatViewInflater;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.util.Map;

@SuppressWarnings("unused")
public class SkinViewInflater extends AppCompatViewInflater {

    public static final String FLAG_BACKGROUND = "0";
    public static final String FLAG_SRC = "1";
    public static final String FLAG_TINT = "2";
    public static final String FLAG_TEXT_COLOR = "3";

    public static final String SKIN_FLAG_NAME = "mxSkin";
    public static final String SKIN_FLAG_NAMESPACE = "http://schemas.android.com/apk/res-auto";


    private static final Class<?>[] sConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = new int[]{android.R.attr.onClick};

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    private static final String LOG_TAG = "AppCompatViewInflater";

    private static final Map<String, Constructor<? extends View>> sConstructorMap
            = new ArrayMap<>();

    private final Object[] mConstructorArgs = new Object[2];

    @Nullable
    @Override
    protected View createView(Context context, String name, AttributeSet attrs) {
        try {
            View view = createViewFromTag(context, name, attrs);

            if (view != null) {
                initSkin(context, view, attrs);
            }

            return view;
        } catch (Exception e) {
            return null;
        }
    }

    private View createViewFromTag(Context context, String name, AttributeSet attrs) {
        if (name.equals("view")) {
            name = attrs.getAttributeValue(null, "class");
        }

        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attrs;

            if (-1 == name.indexOf('.')) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    final View view = createViewByPrefix(context, name, sClassPrefixList[i]);
                    if (view != null) {
                        return view;
                    }
                }
                return null;
            } else {
                return createViewByPrefix(context, name, null);
            }
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        } finally {
            // Don't retain references on context.
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }

    private View createViewByPrefix(Context context, String name, String prefix)
            throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = sConstructorMap.get(name);

        try {
            if (constructor == null) {
                // Class not found in the cache, see if it's real, and try to add it
                Class<? extends View> clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(sConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e) {
            // We do not want to catch these, lets return null and let the actual LayoutInflater
            // try
            return null;
        }
    }

    private void initSkin(Context context, View view, AttributeSet attrs) {
        if (!(context instanceof SkinDelegate))
            return;

        String skinFlag = attrs.getAttributeValue(SKIN_FLAG_NAMESPACE, SKIN_FLAG_NAME);
        if (TextUtils.isEmpty(skinFlag))
            return;

        if (FLAG_BACKGROUND.equals(skinFlag)) {
            updateBackground(context, view, attrs);
        } else if (FLAG_SRC.equals(skinFlag)) {
            updateSrc(context, (ImageView) view, attrs);
        } else if (FLAG_TINT.equals(skinFlag)) {
            updateTint(context, (ImageView) view, attrs);
        } else if (FLAG_TEXT_COLOR.equals(skinFlag)) {
            updateTextColor(context, (TextView) view, attrs);
        }
    }

    private void updateBackground(Context context, View view, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.lpf.tools.utilskin.R.styleable.SkinSupport, 0, 0);

        try {
            int id = a.getResourceId(com.lpf.tools.utilskin.R.styleable.SkinSupport_android_background, 0);
            if (id == 0)
                return;

            String newName = ((SkinDelegate) context).getResourceNameForSkin(id);
            if (TextUtils.isEmpty(newName))
                return;

            int drawableId = ThemeResourceUtil.getDrawableId(context, newName);

            if (drawableId == 0)
                return;

            view.setBackgroundResource(drawableId);
        } finally {
            a.recycle();
        }
    }

    private void updateSrc(Context context, ImageView imageView, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.lpf.tools.utilskin.R.styleable.SkinImageView, 0, 0);

        try {
            int id = a.getResourceId(com.lpf.tools.utilskin.R.styleable.SkinImageView_android_src, 0);
            if (id == 0)
                return;

            String newName = ((SkinDelegate) context).getResourceNameForSkin(id);
            if (TextUtils.isEmpty(newName))
                return;

            int drawableId = ThemeResourceUtil.getDrawableId(context, newName);

            if (drawableId == 0)
                return;

            imageView.setImageResource(drawableId);

//            Drawable drawable = context.getResources().getDrawable(drawableId);
//            imageView.setImageDrawable(drawable);
        } finally {
            a.recycle();
        }
    }

    private void updateTint(Context context, ImageView imageView, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.lpf.tools.utilskin.R.styleable.SkinTintImageView, 0, 0);

        try {
            int id = a.getResourceId(com.lpf.tools.utilskin.R.styleable.SkinTintImageView_android_tint, 0);
            if (id == 0)
                return;

            String newName = ((SkinDelegate) context).getResourceNameForSkin(id);
            if (TextUtils.isEmpty(newName))
                return;

            int colorId = ThemeResourceUtil.getColorId(context, newName);
            if (colorId == 0)
                return;

            ColorStateList colorStateList = AppCompatResources.getColorStateList(context, colorId);

            ImageViewCompat.setImageTintList(imageView, colorStateList);
        } finally {
            a.recycle();
        }
    }

    private void updateTextColor(Context context, TextView textView, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.lpf.tools.utilskin.R.styleable.SkinTextView, 0, 0);

        try {
            int id = a.getResourceId(com.lpf.tools.utilskin.R.styleable.SkinTextView_android_textColor, 0);
            if (id == 0)
                return;

            String newName = ((SkinDelegate) context).getResourceNameForSkin(id);
            if (TextUtils.isEmpty(newName))
                return;

            int colorId = ThemeResourceUtil.getColorId(context, newName);
            if (colorId == 0)
                return;

            textView.setTextColor(context.getResources().getColor(colorId));
        } finally {
            a.recycle();
        }
    }
}
