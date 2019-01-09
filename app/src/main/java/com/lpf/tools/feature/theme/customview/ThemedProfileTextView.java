package com.lpf.tools.feature.theme.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class ThemedProfileTextView extends android.support.v7.widget.AppCompatTextView {
    public ThemedProfileTextView(Context context) {
        super(context);
    }

    public ThemedProfileTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThemedProfileTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
//        if (left == null && top == null && right == null && bottom == null) {
//            super.setCompoundDrawables(left, top, right, bottom);
//            return;
//        }
//
//        TypedArray a  = getContext().obtainStyledAttributes(new int[] {R.attr.mxProfileIconFilterColor} );
//
//        PorterDuffColorFilter colorFilter;
//        try {
//            int color = a.getColor(0, 0);
//            if (color != 0)
//                colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
//            else {
//                super.setCompoundDrawables(left, top, right, bottom);
//                return;
//            }
//
//            if (left != null) {
//                left = left.mutate();
//                left.setColorFilter(colorFilter);
//            }
//
//            if (top != null) {
//                top = top.mutate();
//                top.setColorFilter(colorFilter);
//            }
//
//            if (right != null) {
//                right = right.mutate();
//                right.setColorFilter(colorFilter);
//            }
//
//            if (bottom != null) {
//                bottom = bottom.mutate();
//                bottom.setColorFilter(colorFilter);
//            }
//        }catch (Exception e) {
//
//        }finally {
//            a.recycle();
//        }
//
//        super.setCompoundDrawables(left, top, right, bottom);
    }
}
