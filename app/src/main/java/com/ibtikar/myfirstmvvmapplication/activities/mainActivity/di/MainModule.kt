package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.di

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainContract
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainModel
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainViewModel
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.retrofit.MainApi
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import com.ibtikar.myfirstmvvmapplication.vm.vmModule.ViewModelKey
import com.ibtikar.myfirstmvvmapplication.vm.vmModule.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = arrayOf(ViewModelModule::class))
class MainModule(var activity: Activity) {

    @Provides
    @ActivityScope
    fun provideMainActivityContext(): FragmentActivity {
        return activity as FragmentActivity
    }

    @Provides
    @ActivityScope
    fun getMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create<MainApi>(MainApi::class.java)
    }

    @Provides
    @ActivityScope
    fun getMainModel(mainModel: MainModel): MainContract.Model {
        return mainModel
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel {
        return mainViewModel
    }


    @Provides
    @ActivityScope
    fun provideMainViewModel(activity: FragmentActivity, vmFactory: ViewModelProvider.Factory): MainContract.ViewModel {
        return ViewModelProviders.of(activity, vmFactory).get(MainViewModel::class.java)
    }


}