package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.LiveData
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import io.reactivex.Completable


interface MainContract {

    interface View {

    }

    interface Model {
        fun getItems(): Completable
        fun loadItemsFromDataBase(): LiveData<List<Item>>
    }

    interface ViewModel {
        fun callDataFromApi(): Completable
        fun loadItemsFromDb(): LiveData<List<Item>>
    }
}

