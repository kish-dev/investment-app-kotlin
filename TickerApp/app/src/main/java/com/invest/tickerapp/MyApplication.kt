package com.invest.tickerapp

import android.app.Application
import androidx.room.Room
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.CompanyDatabase
import com.invest.tickerapp.model.data.ConfigureViewModelAdapter
import com.invest.tickerapp.model.data.DataStoreScope
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class MyApplication @Inject constructor(): Application()
