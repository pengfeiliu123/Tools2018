package com.lpf.tools.feature.dbdemo.litepal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lpf.tools.R

import com.lpf.tools.db.Book
import kotlinx.android.synthetic.main.activity_db_litepal.*
import org.jetbrains.anko.toast
import org.litepal.crud.DataSupport
import org.litepal.tablemanager.Connector

class LitepalDbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_litepal)


        Connector.getDatabase();

        btnAdd.setOnClickListener {
            val book = Book()
            book.name = "book name 1"
            book.author = "author 1"
            book.save()
        }

        btnUpdate.setOnClickListener {
            val book = Book()
            book.price = "2.0"
            book.updateAll("name = ?", "book name 1")
        }

        btnFind.setOnClickListener {
            var books = DataSupport.findAll(Book::class.java)
            books.forEach {
                toast(it.name + " ->" + it.price)
            }
        }

        btnDelete.setOnClickListener{
            DataSupport.deleteAll(Book::class.java,"name =?","book name 1")
        }
    }
}
