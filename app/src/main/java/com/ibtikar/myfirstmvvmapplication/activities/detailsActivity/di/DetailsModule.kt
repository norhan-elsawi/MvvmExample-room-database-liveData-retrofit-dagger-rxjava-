package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.di

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsContract
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsModel
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsViewModel
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class DetailsModule(var activity: Activity) {

    @Provides
    @ActivityScope
    fun provideDetailsActivityContext(): FragmentActivity {
        return activity as FragmentActivity
    }

    @Provides
    @ActivityScope
    fun getDetailsModel(detailsModel: DetailsModel): DetailsContract.Model {
        return detailsModel
    }


    @Provides
    @ActivityScope
    fun provideDetailsViewModel(activity: FragmentActivity, vmFactory: ViewModelProvider.Factory): DetailsContract.ViewModel {
        Log.e("details factory", vmFactory.toString())
        return ViewModelProviders.of(activity, vmFactory).get(DetailsViewModel::class.java)
    }


}