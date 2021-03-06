package com.invest.tickerapp.model

data class Company(
    var companyName: String,
    var companyTicker: String,
    var cost: String,
    var deltaCost: String,
    var logoIdImage: Int,
    var star: Boolean
)
