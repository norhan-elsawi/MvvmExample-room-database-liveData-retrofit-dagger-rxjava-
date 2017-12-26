package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm

import android.arch.lifecycle.LiveData
import android.util.Log
import com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.retrofit.MainApi
import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.DataManager
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao.ItemDao
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainModel @Inject constructor(var dataManager: DataManager, var itemDao: ItemDao,
                                    var mainApi: MainApi) :MainContract.Model{


    override fun getItems(): Completable {
        return mainApi.getItemList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess { itemList ->
                    for (i in itemList.listIterator()) {
                        Log.e("items", "${i.itemName}")
                    }
                    insertDataToDatabase(itemList)
                }
                .doOnError { error -> error.printStackTrace() }
                .toCompletable()

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

   override fun loadItemsFromDataBase(): LiveData<List<Item>> {
        return itemDao.getAllItems()
    }

}