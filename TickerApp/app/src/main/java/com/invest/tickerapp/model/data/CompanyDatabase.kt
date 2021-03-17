package com.invest.tickerapp.model.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Company::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {
    abstract fun companyDao() : CompanyDao
}