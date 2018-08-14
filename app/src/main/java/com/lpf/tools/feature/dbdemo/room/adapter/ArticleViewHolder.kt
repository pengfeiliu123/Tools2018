package com.lpf.tools.feature.dbdemo.room.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.lpf.tools.R
import com.lpf.tools.feature.dbdemo.room.ArticleEntity
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by liupengfei on 2018/8/7 15:34.
 */

class ArticleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycle_article_item, parent, false)) {

    private val titleView = itemView.findViewById<TextView>(R.id.value_title)
    private val authorView = itemView.findViewById<TextView>(R.id.value_author)
    private val timeView = itemView.findViewById<TextView>(R.id.value_time)
    private val typeView = itemView.find<TextView>(R.id.value_type)

    var article: ArticleEntity? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(article: ArticleEntity) {
        this.article = article
        titleView.text = article?.title
        authorView.text = article?.author
        article?.timeStamp?.let {
            timeView.text = SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINESE).format(Date(it))
        }
        typeView.text = article?.type
    }

}