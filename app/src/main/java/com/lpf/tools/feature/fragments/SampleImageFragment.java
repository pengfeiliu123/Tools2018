package com.lpf.tools.feature.fragments;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lpf.tools.R;
import com.lpf.utilcode.util.DisplayUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Created by lpf on 2019/5/11 06:06.
 */
public class SampleImageFragment extends DialogFragment {

    /* ======================================================= */
    /* Fields                                                  */
    /* ======================================================= */

    /**
     * 多张图片ViewPager
     */
    private ViewPager mSampleViewPager;

    /**
     * 单张图片
     */
    private ImageView mSampleImageView;

    /**
     * 关闭按钮
     */
    private ImageView mImageClose;

    /**
     * 单个图片的地址
     */
    private String mImageUrl;

    /**
     * 多个图片的地址
     */
    private ArrayList<String> mImageUrls;

    public static final String IMAGE_URL = "imageUrl";

    public static final String IMAGE_URL_ARRAY = "imageUrls";

    private int mScreenWidth;

    private DisplayMetrics mDisplayMetrics;

    private WindowManager mWindowManager;

    private Display mDisplay;

    /* ======================================================= */
    /* Constructors                                            */
    /* ======================================================= */

    public SampleImageFragment() {
        mImageUrls = new ArrayList<>(5);
    }


    /* ======================================================= */
    /* Override/Implements Methods                             */
    /* ======================================================= */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDisplayMetrics = new DisplayMetrics();
        if (getActivity() == null) {
            return;
        }
        mWindowManager = getActivity().getWindowManager();
        if (mWindowManager == null) {
            return;
        }
        mDisplay = mWindowManager.getDefaultDisplay();
        if (mDisplay == null) {
            return;
        }
        mDisplay.getMetrics(mDisplayMetrics);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            mImageUrl = bundle.getString(IMAGE_URL);
            mImageUrls = bundle.getStringArrayList(IMAGE_URL_ARRAY);
        }

        View view = inflater.inflate(R.layout.fragment_sample_image, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }
            window.setLayout((int) (mDisplayMetrics.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }


    /* ======================================================= */
    /* Private Methods                                         */
    /* ======================================================= */

    private void initView(View view) {
        mSampleViewPager = view.findViewById(R.id.sample_image_viewpager);
        mSampleImageView = view.findViewById(R.id.sample_img);
        mImageClose = view.findViewById(R.id.sample_image_close);

        initClickEvent();

        if (mImageUrls != null) {
            initViewPager();
            mSampleViewPager.setVisibility(View.VISIBLE);
            mSampleImageView.setVisibility(View.GONE);
        } else if (mImageUrl != null) {
            initSingleImage();
            mSampleImageView.setVisibility(View.VISIBLE);
            mSampleViewPager.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {

        if (mImageUrls != null) {
            mScreenWidth = DisplayUtils.px2Dip(getContext(), mDisplayMetrics.widthPixels);
            ImageViewPagerAdapter mAdapter = new ImageViewPagerAdapter(getContext(), mImageUrls, mScreenWidth);
            mSampleViewPager.setAdapter(mAdapter);
            mSampleViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener());
        }

        mImageClose.setVisibility(View.VISIBLE);
    }

    /**
     * 加载单张图片
     */
    private void initSingleImage() {

        if (TextUtils.isEmpty(mImageUrl)) {
            mImageUrl = "";
        }

        Context context = getActivity();
        if (context == null) {
            dismiss();
            return;
        }

        RequestOptions options = RequestOptions
            .bitmapTransform(new RoundedCorners(DisplayUtils.dip2Px(getActivity(), 16)))
            .error(R.drawable.test_image);

        RequestListener<Drawable> listener = new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mImageClose.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mImageClose.setVisibility(View.VISIBLE);
                return false;
            }
        };

        Glide.with(context)
            .load(mImageUrl)
            .listener(listener)
            .apply(options)
            .into(mSampleImageView);

    }

    /**
     * 关闭图片
     */
    private void initClickEvent() {
        mImageClose.setOnClickListener(v -> dismiss());
    }
}
