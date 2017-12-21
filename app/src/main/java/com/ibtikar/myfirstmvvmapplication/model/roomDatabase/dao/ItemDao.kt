package com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item


@Dao
interface ItemDao {

    @Query("SELECT * FROM ${Item.TABLE_NAME}")
    fun getAllItems(): LiveData<List<Item>>

//    @Query("SELECT * FROM ${Item.TABLE_NAME}  where ${Item.ID} = :arg0 ")
//    fun getItemById(itemId: Int):LiveData <Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items:List<Item>)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM ${Item.TABLE_NAME}")
    fun deleteAllItems()
}