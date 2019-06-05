package com.lpf.tools.feature.dialog

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.lpf.tools.R
import kotlinx.android.synthetic.main.activity_dialog_test.*

class DialogTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_test)


        var checkedItem = 0

        btnSingleChoice.setOnClickListener{
            var checkedItem = 0

            val builder = AlertDialog.Builder(DialogTestActivity@this)
            builder.setTitle("你现在的居住地是：")
            val cities = arrayOf("北京", "上海", "广州", "深圳", "杭州", "天津", "成都")

            builder.setSingleChoiceItems(cities, checkedItem) { dialog, which -> checkedItem = which }
            //设置正面按钮
            builder.setPositiveButton("确认") { dialog, which -> dialog.dismiss() }
            //设置反面按钮
            builder.setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
            val dialog = builder.create()
            dialog.show()
        }

        btnMultiChoice.setOnClickListener{

            val builder = AlertDialog.Builder(DialogTestActivity@this)
            builder.setTitle("请选择你喜欢的颜色：")
            val colors = arrayOf("红色", "橙色", "黄色", "绿色", "蓝色", "靛色", "紫色")
            val myColors = ArrayList<String>()
            builder.setMultiChoiceItems(colors, null) { dialog, which, isChecked ->
                if (isChecked) {
                    myColors.add(colors[which])
                } else {
                    myColors.remove(colors[which])
                }
            }

            //设置正面按钮
            builder.setPositiveButton("确认") { dialog, which -> dialog.dismiss() }
            //设置反面按钮
            builder.setNegativeButton("取消") { dialog, which ->
                myColors.clear()
                dialog.dismiss()
            }
            builder.show()
        }
    }
}
