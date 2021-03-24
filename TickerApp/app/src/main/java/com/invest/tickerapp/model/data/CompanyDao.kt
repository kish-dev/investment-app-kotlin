package com.invest.tickerapp.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company ORDER BY company_name")
    fun getStockCompanies(): Flow<List<Company>>

    @Query("SELECT * FROM company WHERE star = 1 ORDER BY company_name")
    fun getFavoriteCompanies(): Flow<List<Company>>

    @Insert(onConflict = REPLACE)
    fun insert(company: Company)

    @Update
    fun update(company: Company)
}