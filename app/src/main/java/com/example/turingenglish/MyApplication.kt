package com.example.turingenglish

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by Ethan On 2022/5/13 18:50
 * God bless my code!
 */
class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}