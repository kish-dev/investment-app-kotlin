package com.invest.tickerapp.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company")
    fun getAllCompanies() : LiveData<List<Company>>

    @Insert(onConflict = REPLACE)
    fun insert(company: Company)
}