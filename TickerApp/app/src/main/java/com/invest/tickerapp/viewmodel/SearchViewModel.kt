package com.invest.tickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invest.tickerapp.BuildConfig
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.ViewModelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    var adapter: ViewModelAdapter
) : ViewModel() {

    private val _searchList = MutableLiveData<MutableList<Company>>()
    val searchList: LiveData<MutableList<Company>>
        get() = _searchList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val listStockServer =
                adapter.getStockListServer(apiKeyToFinHubApi, this).first()
            _searchList.postValue(listStockServer.toMutableList())
        }

        viewModelScope.launch(Dispatchers.IO) {
            adapter.getStockListFlow().drop(1).collect {
                _searchList.postValue(it.toMutableList())
            }
        }
    }

    fun search(searchQueryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            adapter.getSearchListFlow(searchQueryString).drop(1).collect {
                _searchList.postValue(it.toMutableList())
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