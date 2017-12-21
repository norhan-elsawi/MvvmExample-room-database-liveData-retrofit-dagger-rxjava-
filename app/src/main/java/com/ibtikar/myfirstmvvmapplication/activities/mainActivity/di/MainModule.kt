package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainViewModel
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule(var activity: Activity) {

    @Provides
    @ActivityScope
    fun provideMainActivityContext(): FragmentActivity {
        return activity as FragmentActivity
    }


    @Provides
    @ActivityScope
    fun provideViewModel(activity: FragmentActivity, vmFactory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProviders.of(activity, vmFactory).get(MainViewModel::class.java)
    }

}