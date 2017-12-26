package com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.di

import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsActivity
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsContract
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import com.ibtikar.myfirstmvvmapplication.di.scopes.ActivityScope
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(DetailsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface DetailsComponent {
    fun inject(activity: DetailsActivity)
}
