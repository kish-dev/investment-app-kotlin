package com.invest.tickerapp.model.data

import android.util.Log
//import com.invest.tickerapp.MyApplication
import com.invest.tickerapp.model.network.NetworkService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
//import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
//import javax.inject.Inject
import kotlin.math.absoluteValue

class ConfigureViewModelAdapter @Inject constructor(
    var companyDataSource: CompanyLocalDataSource
) {

    private val service = NetworkService

    private suspend fun calculateCostAndDeltaCost(
        company: Company,
        ticker: String,
        token: String
    ): Company {
        try {
            val result = service.finHubApi()!!.getQuote(ticker, token)

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
            Log.e("CompanyLoader", "${e.message}")
            e.printStackTrace()
        }
        return company
    }

    suspend fun getStockListFlow() =
        companyDataSource.getStockList()

    suspend fun getFavoriteListFlow() =
        companyDataSource.getFavoriteList()

    suspend fun getStockListServer(token: String, scope: CoroutineScope) =
        companyDataSource.getStockList().map {
            it.toCost(token, scope)
        }

    suspend fun getFavoriteListServer(token: String, scope: CoroutineScope) =
        companyDataSource.getFavoriteList().map {
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

    suspend fun <T> executeAll(
        list: List<suspend () -> T>,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): List<T> {
        val listJob = list.map {
            CoroutineTask(it)
        }
        listJob.map {
            scope.async(dispatcher) { it.execute() }
        }.awaitAll()
        return listJob.map { it.result }
    }

    class CoroutineTask<T>(private val job: suspend () -> T) {
        private var _result: T? = null
        val result: T
            get() = _result!!

        suspend fun execute(): T {
            _result = job.invoke()
            return result
        }
    }

}