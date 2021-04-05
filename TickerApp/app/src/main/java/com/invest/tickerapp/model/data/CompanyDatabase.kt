package com.invest.tickerapp.model.data

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Company::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
}

fun <T : RoomDatabase> RoomDatabase.Builder<T>.addCreateCallback(insert: () -> Unit) =
        this.addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Log.e("RoomDatabase", "ПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БДПЕРЕСОЗДАНИЕ БД")
                super.onCreate(db)
                insert.invoke()
            }
        })
