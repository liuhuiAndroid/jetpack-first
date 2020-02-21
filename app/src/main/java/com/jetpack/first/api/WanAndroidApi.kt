package com.jetpack.first.api

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber

interface WanAndroidApi {

    @GET("/wxarticle/chapters/json")
    fun wxarticleChaptersJson(): Call<ChaptersResponse>

    class ChaptersResponse{

    }

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com/"

        fun create(): WanAndroidApi = create(HttpUrl.parse(BASE_URL)!!)

        fun create(httpUrl: HttpUrl): WanAndroidApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Timber.i("API $it")
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanAndroidApi::class.java)
        }
    }
}