package com.lpf.tools.feature.dbdemo.room

import android.arch.persistence.room.RoomDatabase

/**
 * Created by liupengfei on 2018/8/7 12:01.
 */
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    fun genArticleList(articleDao: ArticleDao): List<ArticleEntity> {
        articleDao.deleteAll(articleDao.getAll())
        val articleList = mutableListOf<ArticleEntity>()

        val authorPrefix = "作者"
        val titlePrefix = "标题"
        val typePrefix = "类别"
        val timeStampBase = 1531548138000L

        for (i in 0 until 100) {
            var articleEntity = ArticleEntity()
            articleEntity.id = i.toString()
            articleEntity.author = "$authorPrefix ${articleEntity.id}"
            articleEntity.title = "$titlePrefix ${articleEntity.id}"
            articleEntity.type = "$typePrefix ${articleEntity.id}"
            articleEntity.timeStamp = timeStampBase + i * 1000L
            articleList.add(articleEntity)
        }

        return articleList
    }
}
