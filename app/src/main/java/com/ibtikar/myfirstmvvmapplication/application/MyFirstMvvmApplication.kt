package com.ibtikar.myfirstmvvmapplication.application

import android.app.Application
import android.databinding.DataBindingUtil
import com.ibtikar.myfirstmvvmapplication.dataBindingDi.component.DaggerBindingComponent
import com.ibtikar.myfirstmvvmapplication.di.Injector


class MyFirstMvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeApplicationComponent()

        val bindingComponent = DaggerBindingComponent.builder()
                .applicationComponent(Injector.INSTANCE.appComponent!!)
                .build()

        DataBindingUtil.setDefaultComponent(bindingComponent)

    }

    private fun initializeApplicationComponent() {
        if (Injector.INSTANCE.appComponent == null) {
            Injector.INSTANCE.initializeAppComponent(this)
        }
    }
}