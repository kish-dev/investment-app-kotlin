package com.invest.tickerapp.model.di

import com.invest.tickerapp.model.data.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JSONFinHubApi {
    @GET("quote")
    suspend fun getQuote(@Query("symbol") symbol: String,
               @Query("token") token: String) : Quote
}