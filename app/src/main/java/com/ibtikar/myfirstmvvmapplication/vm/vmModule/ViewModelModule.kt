package com.ibtikar.myfirstmvvmapplication.vm.vmModule

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ibtikar.myfirstmvvmapplication.activities.detailsActivity.mvvm.DetailsViewModel
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.MainViewModel
import com.ibtikar.myfirstmvvmapplication.vm.vmFactory.VmFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: VmFactory): ViewModelProvider.Factory

}