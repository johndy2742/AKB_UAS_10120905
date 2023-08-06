package com.example.uas_10120905_johndypanca.ui.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://valorant-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

}
