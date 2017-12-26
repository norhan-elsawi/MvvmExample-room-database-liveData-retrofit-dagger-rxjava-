package com.ibtikar.myfirstmvvmapplication.di.modules

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibtikar.myfirstmvvmapplication.BuildConfig
import com.ibtikar.myfirstmvvmapplication.di.scopes.ApplicationScope
import com.ibtikar.myfirstmvvmapplication.utils.BooleanAsIntAdapter
import com.ibtikar.myfirstmvvmapplication.utils.CacheUtils
import com.ibtikar.myfirstmvvmapplication.utils.Constants
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule(var baseUrl: String) {

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun providePicasso(client: OkHttpClient, app: Application): Picasso {
        val cache = CacheUtils.createDefaultCacheDir(app)
        val picassoOkHttpClient = client
                .newBuilder()
                .cache(Cache(cache, CacheUtils.calculateDiskCacheSize(cache)))
                .build()

        val builder = Picasso.Builder(app)
                .downloader(OkHttp3Downloader(picassoOkHttpClient))
                .listener { picasso, uri, exception -> Log.e("NetworkModule", "Error while loading image $uri") }

        if (BuildConfig.DEBUG) {
            builder.indicatorsEnabled(BuildConfig.DEBUG)
                    .loggingEnabled(BuildConfig.DEBUG)
        }

        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat(Constants.DATE_FORMAT)
        val booleanAsIntAdapter = BooleanAsIntAdapter()
        gsonBuilder.registerTypeAdapter(Boolean::class.java, booleanAsIntAdapter)
        gsonBuilder.registerTypeAdapter(Boolean::class.javaPrimitiveType, booleanAsIntAdapter)
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }


}
