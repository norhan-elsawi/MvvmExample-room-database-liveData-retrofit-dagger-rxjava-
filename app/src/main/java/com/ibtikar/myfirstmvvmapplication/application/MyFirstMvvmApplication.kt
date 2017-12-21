package com.ibtikar.myfirstmvvmapplication.application

import android.app.Application
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.di.Injector
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item


class MyFirstMvvmApplication :Application(){

    override fun onCreate() {
        super.onCreate()

        initializeApplicationComponent()
    }

    private fun initializeApplicationComponent() {
        if (Injector.INSTANCE.appComponent == null) {
            Injector.INSTANCE.initializeAppComponent(this)
        }
    }
}