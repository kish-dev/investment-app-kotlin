package com.invest.tickerapp.model.network

import com.invest.tickerapp.model.di.JSONFinHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkService {

    private const val BASE_URL: String = "https://finnhub.io/api/v1/"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun getJSONApi(): JSONFinHubApi? {
        return retrofit.create(JSONFinHubApi::class.java)
    }

}



