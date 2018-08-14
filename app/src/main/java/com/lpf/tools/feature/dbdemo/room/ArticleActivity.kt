package com.lpf.tools.feature.dbdemo.room

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lpf.tools.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        btn_db_data.setOnClickListener {
            startActivity(Intent(this, DbDataPagingActivity::class.java))
        }

        btn_local_data.setOnClickListener {
            startActivity(Intent(this, LocalDataPagingActivity::class.java))
        }
    }
}
