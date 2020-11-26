package com.github.mohammadsianaki.tavansazan.di

import android.content.Context
import android.util.Log
import com.github.mohammadsianaki.core.network.adapter.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api-dot-rafiji-staging.appspot.com/customer/v2/"

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        networkResponseCallAdapterFactory: NetworkResponseAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(networkResponseCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
//        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(5, TimeUnit.SECONDS)
        writeTimeout(5, TimeUnit.SECONDS)
        readTimeout(5, TimeUnit.SECONDS)
//        addInterceptor(tokenInterceptor)
//        addNetworkInterceptor(connectivityInterceptor)
        addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("<<<network>>>", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor(ChuckInterceptor(context)).build()
    }.build()

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesNetworkResponseCallAdapterFactory() = NetworkResponseAdapterFactory.create()
}
