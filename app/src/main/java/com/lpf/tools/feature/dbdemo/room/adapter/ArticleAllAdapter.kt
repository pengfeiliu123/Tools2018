package com.lpf.tools.feature.dbdemo.room.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lpf.tools.feature.dbdemo.room.ArticleEntity

/**
 * Created by liupengfei on 2018/8/7 15:49.
 */
class ArticleAllAdapter : RecyclerView.Adapter<ArticleViewHolder>() {

    var dataList: List<ArticleEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindTo(dataList[position])
    }
}