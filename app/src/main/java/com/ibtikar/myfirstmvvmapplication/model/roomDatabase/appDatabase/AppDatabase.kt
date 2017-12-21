package com.ibtikar.myfirstmvvmapplication.model.roomDatabase.appDatabase

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.ibtikar.myfirstmvvmapplication.model.pojos.Item
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao.ItemDao


@Database(entities = arrayOf(Item::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}