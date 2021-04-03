package com.invest.tickerapp.model.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyLocalDataSource @Inject constructor(
        private val companyDao: CompanyDao
) : CompanyDataSource {

    override suspend fun addCompany(company: Company) = companyDao.insert(company)

    override suspend fun update(company: Company) = companyDao.update(company)

    override suspend fun getStockList() = companyDao.getStockCompanies()

    override suspend fun getFavoriteList() = companyDao.getFavoriteCompanies()

    override suspend fun getSearchList(searchQueryString: String) =
        companyDao.getSearchCompanies(searchQueryString)

}
