package com.lpf.tools.feature.timepickerdialog

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.jzxiang.pickerview.listener.OnDateSetListener
import kotlinx.android.synthetic.main.activity_time_picker_dialog.*
import java.text.SimpleDateFormat


class TimePickerDialogActivity : AppCompatActivity(), View.OnClickListener, OnDateSetListener {

    private lateinit var mDialogAll : TimePickerDialog
    private lateinit var mDialogYearMonth : TimePickerDialog
    private lateinit var mDialogYearMonthDay : TimePickerDialog
    private lateinit var mDialogMonthDayHourMinute : TimePickerDialog
    private lateinit var mDialogHourMinute : TimePickerDialog

    init{

        val tenYears = 10L * 365 * 1000 * 60 * 60 * 24L

        mDialogAll = TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("Cancel")
                .setSureStringId("Sure")
                .setTitleStringId("TimePicker")
                .setYearText("Year")
                .setMonthText("Month")
                .setDayText("Day")
                .setHourText("Hour")
                .setMinuteText("Minute")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(Color.parseColor("#000000"))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(Color.parseColor("#CCCCCC"))
                .setWheelItemTextSelectorColor(Color.parseColor("#FF0000"))
                .setWheelItemTextSize(12)
                .build()


        mDialogYearMonth = TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setThemeColor(Color.parseColor("#FFFF00"))
                .setCallBack(this)
                .build()
        mDialogYearMonthDay = TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build()

        mDialogMonthDayHourMinute = TimePickerDialog.Builder()
                .setType(Type.MONTH_DAY_HOUR_MIN)
                .setCallBack(this)
                .build()

        mDialogHourMinute = TimePickerDialog.Builder()
                .setType(Type.HOURS_MINS)
                .setCallBack(this)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lpf.tools.R.layout.activity_time_picker_dialog)


        btn_all.setOnClickListener(this)
        btn_year_month_day.setOnClickListener(this)
        btn_year_month.setOnClickListener(this)
        btn_hour_minute.setOnClickListener(this)
        btn_month_day_hour_minute.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            com.lpf.tools.R.id.btn_all -> {
                mDialogAll.show(supportFragmentManager,"all")
            }
            com.lpf.tools.R.id.btn_year_month -> {
                mDialogYearMonth.show(supportFragmentManager,"year_month")
            }
            com.lpf.tools.R.id.btn_year_month_day -> {
                mDialogYearMonthDay.show(supportFragmentManager,"year_month_day")
            }
            com.lpf.tools.R.id.btn_month_day_hour_minute -> {
                mDialogMonthDayHourMinute.show(supportFragmentManager,"month_day_hour_minute")
            }
            com.lpf.tools.R.id.btn_hour_minute -> {
                mDialogHourMinute.show(supportFragmentManager,"hour_minute")
            }


        }
    }

    override fun onDateSet(timePickerView: TimePickerDialog?, millseconds: Long) {
        tv_time.text = getDateToString(millseconds)
    }

    private fun getDateToString(millseconds: Long): CharSequence? {
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sf.format(millseconds)
    }

}
