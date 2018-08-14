package com.lpf.tools.feature.dbdemo.room

import java.util.concurrent.Executors

/**
 * Created by liupengfei on 2018/8/8 11:27.
 */

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}