package com.ibtikar.myfirstmvvmapplication.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.ibtikar.myfirstmvvmapplication.di.scopes.ApplicationScope
import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.DataManager
import com.ibtikar.myfirstmvvmapplication.model.localDataProvider.PrefHelper
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.appDatabase.AppDatabase
import com.ibtikar.myfirstmvvmapplication.model.roomDatabase.dao.ItemDao
import com.ibtikar.myfirstmvvmapplication.utils.Constants
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(var mApplication: Application) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }


    @Provides
    @ApplicationScope
    fun provideSharedPrefsHelper(sharedPreferences: SharedPreferences): PrefHelper {
        return PrefHelper(sharedPreferences)
    }

    @Provides
    @ApplicationScope
    fun provideDataManager(context: Context, sharedPrefsHelper: PrefHelper): DataManager {
        return DataManager(context, sharedPrefsHelper)
    }

    @ApplicationScope
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "appDatabase.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @ApplicationScope
    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao {
        return db.itemDao()
    }


}