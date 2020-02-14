package com.jetpack.first

import android.app.Application
import android.content.ContextWrapper

private lateinit var INSTANCE: Application

open class JApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}

/**
 * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
 *
 * @return 全局Context对象。
 */
object AppContext : ContextWrapper(INSTANCE)