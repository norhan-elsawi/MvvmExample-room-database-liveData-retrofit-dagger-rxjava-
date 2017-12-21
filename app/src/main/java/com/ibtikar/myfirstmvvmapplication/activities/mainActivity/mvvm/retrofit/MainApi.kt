package com.ibtikar.myfirstmvvmapplication.activities.mainActivity.mvvm.retrofit

import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import io.reactivex.Single
import retrofit2.http.GET



interface MainApi {

    @GET("itemsfeed.php")
    fun getItemList(): Single<List<Item>>
}
