package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.di

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsContract
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsModel
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsViewModel
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import com.ibtikar.myfirstmvvmapplication.vm.vmModule.ViewModelKey
import com.ibtikar.myfirstmvvmapplication.vm.vmModule.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = arrayOf(ViewModelModule::class))
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
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel {
        return detailsViewModel
    }


    @Provides
    @ActivityScope
    fun provideDetailsViewModel(activity: FragmentActivity, vmFactory: ViewModelProvider.Factory): DetailsContract.ViewModel {
        return ViewModelProviders.of(activity, vmFactory).get(DetailsViewModel::class.java)
    }


}