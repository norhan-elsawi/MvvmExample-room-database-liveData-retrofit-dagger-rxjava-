package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.retrofit.MainApi
import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.DataManager
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao.ItemDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainModel @Inject constructor(var dataManager: DataManager, var itemDao: ItemDao,
                                    var mainApi: MainApi) {


    fun getItems(): LiveData<Boolean> {
        val resultCheck: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        resultCheck.value = false
        mainApi.getItemList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ itemList ->
                    for (i in itemList.listIterator()) {
                        Log.e("items", "${i.itemName}")
                    }
                    resultCheck.value = true
                    insertDataToDatabase(itemList)

                }, { error -> error.printStackTrace() })

        return resultCheck
    }

    private fun insertDataToDatabase(itemList: List<Item>) {
        Observable.fromCallable({
            itemDao.insertItems(itemList)
            Log.e("items", "success")
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

    }

    fun loadItemsFromDataBase(): LiveData<List<Item>> {
        return itemDao.getAllItems()
    }

}