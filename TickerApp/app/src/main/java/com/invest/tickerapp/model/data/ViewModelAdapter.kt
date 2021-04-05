package com.invest.tickerapp.model.data

import android.util.Log
//import com.invest.tickerapp.MyApplication
import com.invest.tickerapp.model.network.NetworkService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.lang.Exception
//import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton
//import javax.inject.Inject
import kotlin.math.absoluteValue

class ViewModelAdapter @Inject constructor(
    var companyDataSource: CompanyLocalDataSource
) {

    private val service by lazy{NetworkService.finHubApi()}

    private fun Quote.getDeltaCost() = this.currentPrice.toDouble() - this.closePrice.toDouble()

    private fun Double.getSign() = if (this < 0) '-' else '+'

    private fun Double.format() = String.format("%.2f", this)


    private suspend fun calculateCostAndDeltaCost(
        company: Company,
        ticker: String,
        token: String
    ): Company {
            val result = service.getQuote(ticker, token)
            company.cost = result.currentPrice
            var delta = result.getDeltaCost()
            val sign = delta.getSign()
            delta = delta.absoluteValue
            company.deltaCost = delta.format()
            val percent = (delta / company.cost.toDouble()).format()
            company.deltaCost = "${sign}$${company.deltaCost} (${percent}%)"
            company.cost = "$${company.cost}"
        companyDataSource.update(company)
        return company
    }

    suspend fun getSearchListFlow(searchQueryString: String) =
        companyDataSource.getSearchList(searchQueryString)

    suspend fun getStockListFlow() =
        companyDataSource.getStockList()

    suspend fun getFavoriteListFlow() =
        companyDataSource.getFavoriteList()

    suspend fun getStockListServer(token: String, scope: CoroutineScope) =
       getStockListFlow().map {
            it.toCost(token, scope)
        }

    suspend fun getFavoriteListServer(token: String, scope: CoroutineScope) =
        getFavoriteListFlow().map {
            it.toCost(token, scope)
        }

    private suspend fun List<Company>.toCost(token: String, scope: CoroutineScope) =
        executeAll(map {
            {
                calculateCostAndDeltaCost(
                    it,
                    it.companyTicker,
                    token
                )
            }
        }, scope)

    private suspend fun <T> executeAll(
        list: List<suspend () -> T>,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): List<T> {
        val listJob = list.map {
            scope.async(dispatcher) { it.invoke() }
        }
        listJob.awaitAll()
        return listJob.map { it.getCompleted() }
    }
}
