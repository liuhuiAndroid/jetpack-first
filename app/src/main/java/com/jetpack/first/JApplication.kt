package com.jetpack.first

import android.app.Application
import android.content.ContextWrapper
import timber.log.Timber
import timber.log.Timber.DebugTree

open class JApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        lateinit var INSTANCE: Application
    }

}

/**
 * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
 *
 * @return 全局Context对象。
 */
object AppContext : ContextWrapper(JApplication.INSTANCE)
