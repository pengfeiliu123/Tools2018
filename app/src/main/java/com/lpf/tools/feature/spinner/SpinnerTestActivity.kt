package com.lpf.tools.feature.spinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lpf.tools.R
import com.lpf.utilcode.util.ToastUtil
import kotlinx.android.synthetic.main.activity_spinner_test.*
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener

class SpinnerTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_test)

        initView()
    }

    private fun initView() {

        var dataSets = listOf("One","Two","Three","Four")
        nice_spinner.attachDataSource(dataSets)


        nice_spinner.setOnSpinnerItemSelectedListener { parent, view, position, id ->

            ToastUtil.showLong(SpinnerTestActivity@this,parent.getItemAtPosition(position).toString())
        }

    }
}
