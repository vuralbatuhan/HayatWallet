package com.example.hayatwallet.network

import com.example.hayatwallet.service.ServiceInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private const val BASE_URL = "https://staging-api.cusp.link"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val serviceInterceptor = ServiceInterceptor()

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(serviceInterceptor)
        addInterceptor(interceptor)
    }.build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val service: LoginAPI by lazy {
        retrofit.create(LoginAPI::class.java)
    }
}
