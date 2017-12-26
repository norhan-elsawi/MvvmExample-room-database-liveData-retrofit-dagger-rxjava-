package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.os.Handler
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import io.reactivex.Completable
import javax.inject.Inject


class MainViewModel @Inject constructor(var model: MainContract.Model) : ViewModel(), MainContract.ViewModel {

    val baz = ObservableField<String>()

    init {
        baz.set("logayn")
    }

    override fun callDataFromApi(): Completable {
        return model.getItems()
    }

    override fun loadItemsFromDb(): LiveData<List<Item>> {
        Handler().postDelayed({
            baz.set("nour")
        }, 3000)


        return model.loadItemsFromDataBase()
    }


}