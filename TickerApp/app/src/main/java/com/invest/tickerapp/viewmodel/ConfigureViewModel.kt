package com.invest.tickerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invest.tickerapp.BuildConfig
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.ConfigureViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


class ConfigureViewModel @Inject constructor(
    var adapter: ConfigureViewModelAdapter
) : ViewModel() {

    private val _favoriteList = MutableLiveData<MutableList<Company>>()
    val favoriteList: LiveData<MutableList<Company>>
        get() = _favoriteList

    private val _stocksList = MutableLiveData<MutableList<Company>>()
    val stocksList: LiveData<MutableList<Company>>
        get() = _stocksList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listStockServer = adapter.getStockListServer(Companion.apiKeyToFinHubApi, this).first()
            _stocksList.postValue(listStockServer.toMutableList())
            val listFavoriteServer =
                adapter.getFavoriteListServer(Companion.apiKeyToFinHubApi, this).first()
            _favoriteList.postValue(listFavoriteServer.toMutableList())
        }
        viewModelScope.launch(Dispatchers.IO) {
            adapter.getStockListFlow().drop(1).collect {
                _stocksList.postValue(it.toMutableList())
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            adapter.getFavoriteListFlow().drop(1).collect {
                _favoriteList.postValue(it.toMutableList())
            }
        }
    }

    fun addToFavoriteList(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            adapter.companyDataSource.update(company)
        }
    }

    fun removeFromFavoriteList(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            adapter.companyDataSource.update(company)
        }
    }

    companion object {
        private const val apiKeyToFinHubApi = BuildConfig.API_KEY_TO_FINHUB
    }
}
