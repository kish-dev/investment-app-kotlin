package com.invest.tickerapp.model.data

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("c")
    val currentPrice: String,

    @SerializedName("h")
    val highPriceOfTheDay: String,

    @SerializedName("l")
    val lowPriceOfTheDay: String,

    @SerializedName("pc")
    val closePrice: String,

    @SerializedName("t")
    val tokenOfCompany: String
)
