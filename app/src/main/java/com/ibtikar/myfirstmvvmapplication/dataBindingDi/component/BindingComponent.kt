package com.ibtikar.myfirstmvvmapplication.dataBindingDi.component

import android.databinding.DataBindingComponent
import com.ibtikar.myfirstmvvmapplication.dataBindingDi.adapters.ImageBindingAdapter
import com.ibtikar.myfirstmvvmapplication.dataBindingDi.module.BindingModule
import com.ibtikar.myfirstmvvmapplication.dataBindingDi.scope.DataBinding
import com.ibtikar.myfirstmvvmapplication.di.component.ApplicationComponent
import dagger.Component

@DataBinding
@Component(modules = arrayOf(BindingModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface BindingComponent : DataBindingComponent {
    override fun getImageBindingAdapter(): ImageBindingAdapter
}