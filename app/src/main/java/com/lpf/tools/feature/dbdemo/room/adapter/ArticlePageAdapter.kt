package com.lpf.tools.feature.dbdemo.room.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.lpf.tools.feature.dbdemo.room.ArticleEntity

/**
 * Created by liupengfei on 2018/8/7 15:42.
 */
class ArticlePageAdapter : PagedListAdapter<ArticleEntity, ArticleViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity?, newItem: ArticleEntity?): Boolean =
                    oldItem!!.id == newItem!!.id

            override fun areContentsTheSame(oldItem: ArticleEntity?, newItem: ArticleEntity?): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }


}
