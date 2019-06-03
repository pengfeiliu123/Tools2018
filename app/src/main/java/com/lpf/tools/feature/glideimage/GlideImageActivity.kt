package com.lpf.tools.feature.glideimage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lpf.tools.R
import kotlinx.android.synthetic.main.activity_glide_image.*

class GlideImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_image)


        val drawable = DrawableCreator.create("#F8F8F8", 20)


        Glide.with(this)
                .load(R.mipmap.test)
                .apply(RequestOptions()
                        .placeholder(drawable)
                        .transform(CornerRadiusTransformer(30)))
                .into(imageView)


    }
}
