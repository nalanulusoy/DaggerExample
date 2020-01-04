package com.example.daggerexample


import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class DaggerExampleApplication :Application(){


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

    }



    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}