package com.lpf.tools.feature.dbdemo.room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.lpf.tools.R
import com.lpf.tools.feature.dbdemo.room.adapter.ArticleAllAdapter
import com.lpf.tools.feature.dbdemo.room.adapter.ArticlePageAdapter
import com.lpf.utilcode.util.ToastUtil
import kotlinx.android.synthetic.main.activity_db_data_paging.*

class DbDataPagingActivity : AppCompatActivity() , View.OnClickListener{

    var postList:LiveData<PagedList<ArticleEntity>> ?= null
    lateinit var dataBase:ArticleDatabase

    val pageAdapter = ArticlePageAdapter()
    val allAdapter = ArticleAllAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_data_paging)
        setTitle("Room Db")

        dataBase = Room.databaseBuilder(this.applicationContext,ArticleDatabase::class.java,"db_article").build()
        recycle_article.layoutManager = LinearLayoutManager(this)

        btn_save.setOnClickListener(this)
        btn_page_show.setOnClickListener(this)
        btn_all_show.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_save ->{
                val articleDao = dataBase.articleDao()
                ioThread{
                    articleDao.insertAll(dataBase.genArticleList(articleDao))
                    runOnUiThread {
                        ToastUtil.showShort(this,"添加数据成功")
                    }
                }
            }
            R.id.btn_page_show->{
                recycle_article.adapter = pageAdapter
                val articleDao = dataBase.articleDao()
                val pagedListConfig = PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setPageSize(10)
                        .setInitialLoadSizeHint(20)
                        .build()
                postList = LivePagedListBuilder(articleDao.getAllByDataSource(),pagedListConfig).build()
                postList!!.observe(this, Observer {
                    pageAdapter.submitList(it)
                })
            }
            R.id.btn_all_show->{

            }
        }
    }
}
