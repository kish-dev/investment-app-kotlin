package com.invest.tickerapp.model.data

import kotlinx.coroutines.flow.Flow

interface CompanyDataSource {
    suspend fun addCompany(company: Company)
    suspend fun update(company: Company)
    suspend fun getStockList(): Flow<List<Company>>
    suspend fun getFavoriteList(): Flow<List<Company>>
}