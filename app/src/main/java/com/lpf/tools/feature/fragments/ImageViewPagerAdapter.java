package com.lpf.tools.feature.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lpf.tools.R;
import com.lpf.utilcode.util.DisplayUtils;

/**
 * @author: lpf
 * @time: 2019-06-05 10:29
 * @description: 多张参考图适配器
 */
public class ImageViewPagerAdapter extends PagerAdapter {


    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    private Context mContext;

    private LayoutInflater mInflater;

    /**
     * 要显示的多张图片
     */
    private ArrayList<String> mImageUrls;

    /**
     * 屏幕宽度 dp
     */
    private int mScreenWidth;



    /* ======================================================= */
    /* Constructors                                            */
    /* ======================================================= */

    public ImageViewPagerAdapter(Context mContext, ArrayList<String> mImageUrls, int mScreenWidth) {
        this.mContext = mContext;
        this.mImageUrls = mImageUrls;
        this.mScreenWidth = mScreenWidth;
        mInflater = LayoutInflater.from(mContext);
    }


    /* ======================================================= */
    /* Override/Implements Methods                             */
    /* ======================================================= */

    @Override
    public int getCount() {
        if (mImageUrls == null) {
            return 0;
        }
        return mImageUrls.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mInflater.inflate(R.layout.item_view_community_sample_image, container, false);

        ImageView imageView = itemView.findViewById(R.id.item_sample_img);
        final TextView imageTipTv = itemView.findViewById(R.id.item_sample_img_tip);

        container.addView(itemView);

        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(DisplayUtils.dip2Px(mContext, 16)))
                .error(R.drawable.test_image);

        RequestListener<Drawable> listener = new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                float index = resource.getIntrinsicHeight() / (float) resource.getIntrinsicWidth();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageTipTv.getLayoutParams();
                float topMargin = (float) (mScreenWidth * 0.8 * index) / 2.0f;
                layoutParams.topMargin = DisplayUtils.dip2Px(mContext, (int) topMargin - 20);
                return false;
            }
        };

        Glide.with(mContext)
                .load(mImageUrls.get(position))
                .apply(options)
                .listener(listener)
                .into(imageView);


        String pageIndex = String.valueOf(position + 1);
        String pageCount = "/" + mImageUrls.size();

        SpannableStringBuilder spannable = new SpannableStringBuilder(pageIndex + pageCount);
        int size = DisplayUtils.dip2Px(mContext, 20);
        spannable.setSpan(new AbsoluteSizeSpan(size), 0, pageIndex.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, pageIndex.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        imageTipTv.setText(spannable);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
