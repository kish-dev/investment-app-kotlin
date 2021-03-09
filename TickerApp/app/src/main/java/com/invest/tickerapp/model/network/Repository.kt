package com.invest.tickerapp.model.network

import android.util.Log
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Repository {

    suspend fun calculateCostAndDeltaCost(
        company: Company,
        ticker: String,
        token: String
    ): Company {
        val service = NetworkService.getJSONApi()
        try {
            var result = service!!.getQuote(ticker, token)

            company.cost = result.currentPrice
            var delta = (result.currentPrice.toDouble() - result.closePrice.toDouble())

            val sign = if (delta < 0) '-' else '+'

            if (sign == '-') {
                delta *= -1
            }

            company.deltaCost = String.format("%.2f", delta)

            val percent = delta / company.cost.toDouble()

            val percentString = String.format("%.2f", percent)

            company.deltaCost = ("${sign}$${company.deltaCost} (${percentString}%)")

        } catch (e: Exception) {
            Log.e("CompanyLoader", "${e.message}")
        }
        return company
    }
}