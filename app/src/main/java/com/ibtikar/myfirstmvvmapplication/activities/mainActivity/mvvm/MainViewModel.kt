package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import javax.inject.Inject

class MainViewModel @Inject constructor(var model: MainModel) : ViewModel() {


    fun callDataFromApi(): LiveData<Boolean> {
        return model.getItems()
    }

    fun loadItemsFromDb(): LiveData<List<Item>> {
        return model.loadItemsFromDataBase()
    }
}