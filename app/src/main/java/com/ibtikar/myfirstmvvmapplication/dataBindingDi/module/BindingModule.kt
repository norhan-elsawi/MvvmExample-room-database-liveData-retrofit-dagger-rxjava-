package com.ibtikar.myfirstmvvmapplication.dataBindingDi.module

import com.ibtikar.myfirstmvvmapplication.dataBindingDi.adapters.ImageBindingAdapter
import com.ibtikar.myfirstmvvmapplication.dataBindingDi.scope.DataBinding
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides


@Module
class BindingModule {
    @Provides
    @DataBinding
    fun provideImageBindingAdapter(picasso: Picasso): ImageBindingAdapter {
        return ImageBindingAdapter(picasso)
    }
}