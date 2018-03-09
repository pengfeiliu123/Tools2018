package com.lpf.tools.db

import org.litepal.crud.DataSupport

/**
 * Created by liupengfei on 2018/3/9 15:49.
 */

class Book : DataSupport() {

    val id: Int = 0
    var author: String? = null
    var price: String? = null
    var name: String? = null

}
