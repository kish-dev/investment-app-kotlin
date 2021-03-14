package com.invest.tickerapp.model.data

import com.invest.tickerapp.model.network.NetworkService
import kotlin.math.absoluteValue

object ConfigureViewModelAdapter {

    private val service by lazy{NetworkService.finHubApi()}

    suspend fun calculateCostAndDeltaCost(
        company: Company,
        ticker: String,
        token: String
    ): Company {
        try {
            val result = service!!.getQuote(ticker, token)

            company.cost = result.currentPrice
            var delta = (result.currentPrice.toDouble() - result.closePrice.toDouble())

            val sign = if (delta < 0) '-' else '+'

            delta = delta.absoluteValue

            company.deltaCost = String.format("%.2f", delta)

            val percent = delta / company.cost.toDouble()

            val percentString = String.format("%.2f", percent)


            company.deltaCost = "${sign}$${company.deltaCost} (${percentString}%)"
            company.cost = "$${company.cost}"

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return company
    }
}