package com.ibtikar.myfirstmvvmapplication.di.component

import android.content.Context
import com.google.gson.Gson
import com.ibtikar.myfirstmvvmapplication.di.modules.ApplicationModule
import com.ibtikar.myfirstmvvmapplication.di.modules.NetworkModule
import com.ibtikar.myfirstmvvmapplication.di.scopes.ApplicationScope
import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.DataManager
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao.ItemDao
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface ApplicationComponent {

    fun provideContext(): Context

    fun providePicasso(): Picasso

    fun provideDataManager(): DataManager

    fun provideItemDao(): ItemDao

    fun provideRetrofit(): Retrofit

    fun provideGson(): Gson
}