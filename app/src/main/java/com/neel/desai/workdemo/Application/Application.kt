package com.neel.desai.workdemo.Application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

open class Application : Application() {
    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        MultiDex.install(this)

        Thread.setDefaultUncaughtExceptionHandler { thread, e ->

        }
    }
}