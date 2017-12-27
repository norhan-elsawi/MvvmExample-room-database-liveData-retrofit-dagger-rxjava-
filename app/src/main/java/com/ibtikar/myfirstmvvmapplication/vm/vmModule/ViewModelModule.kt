package com.ibtikar.myfirstmvvmapplication.vm.vmModule

import android.arch.lifecycle.ViewModelProvider
import com.ibtikar.myfirstmvvmapplication.vm.vmFactory.VmFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: VmFactory): ViewModelProvider.Factory

}