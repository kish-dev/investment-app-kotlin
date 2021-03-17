package com.invest.tickerapp.model.network

import com.invest.tickerapp.BuildConfig
import com.invest.tickerapp.model.di.FinHubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val baseUrl: String = BuildConfig.BASE_URL

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun finHubApi(): FinHubApi? {
        return retrofit.create(FinHubApi::class.java)
    }

}



