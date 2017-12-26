package com.ibtikar.myfirstmvvmapplication.vm.vmFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ibtikar.myfirstmvvmapplication.di.scopes.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
class VmFactory @Inject constructor(): ViewModelProvider.Factory {


    @Inject
    lateinit var creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator!!.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}