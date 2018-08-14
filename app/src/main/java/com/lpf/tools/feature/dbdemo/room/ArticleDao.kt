package com.lpf.tools.feature.dbdemo.room

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by liupengfei on 2018/8/7 12:01.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): List<ArticleEntity>


    @Query("SELECT * FROM article")
    fun getAllByDataSource(): DataSource.Factory<Int, ArticleEntity>


    @Insert
    fun insertAll(articleLists: List<ArticleEntity>)

    @Delete
    fun deleteAll(articleLists: List<ArticleEntity>)

}
