package com.zaki.kasirapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor())
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://demo73.energeek.co.id/kasir-app/public/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val services: KasirApi = retrofit.create(KasirApi::class.java)
}