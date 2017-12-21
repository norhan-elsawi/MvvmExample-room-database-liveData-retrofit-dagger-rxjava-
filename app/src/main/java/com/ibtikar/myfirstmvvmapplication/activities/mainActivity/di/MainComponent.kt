package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di

import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainActivity
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}
