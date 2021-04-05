package com.invest.tickerapp.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.Query as Query

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company ORDER BY company_name")
    fun getStockCompanies(): Flow<List<Company>>

    @Query("SELECT * FROM company WHERE star = $TRUE ORDER BY company_name")
    fun getFavoriteCompanies(): Flow<List<Company>>

    @Insert(onConflict = REPLACE)
    fun insert(company: Company)

    @Update(onConflict = REPLACE)
    fun update(company: Company)

    @Query("SELECT * FROM company WHERE company_name LIKE :searchQueryString OR company_ticker LIKE :searchQueryString")
    fun getSearchCompanies(searchQueryString: String) : Flow<List<Company>>
}

const val TRUE = 1
