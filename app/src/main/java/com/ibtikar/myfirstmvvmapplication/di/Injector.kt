package com.ibtikar.myfirstmvvmapplication.di

import com.ibtikar.myfirstmvvmapplication.application.MyFirstMvvmApplication
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import com.ibtikar.myfirstmvvmapplication.di.component.DaggerApplicationComponent
import com.ibtikar.myfirstmvvmapplication.di.modules.ApplicationModule
import com.ibtikar.myfirstmvvmapplication.di.modules.NetworkModule
import com.ibtikar.myfirstmvvmapplication.utils.Constants

enum class Injector {
    INSTANCE;

    var appComponent: ApplicationComponent? = null


    fun initializeAppComponent(application: MyFirstMvvmApplication): ApplicationComponent {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(application))
                .networkModule(NetworkModule(Constants.BASE_URL))
                .build()
        return appComponent!!
    }
}
