package com.invest.tickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.Company.ListOfCompaniesLoader.listOfCompanies
import com.invest.tickerapp.model.data.Company.ListOfCompaniesLoader.listOfTickers
import com.invest.tickerapp.model.data.ConfigureViewModelAdapter
import kotlinx.coroutines.*

class ConfigureViewModel : ViewModel() {
    private val _favoriteList = MutableLiveData<MutableList<Company>>()
    val favoriteList: LiveData<MutableList<Company>>
        get() = _favoriteList

    private val _stocksList = MutableLiveData<List<Company>>()
    val stocksList: LiveData<List<Company>>
        get() = _stocksList

    init {

        CoroutineScope(Dispatchers.Default).launch {
           val list = withContext(Dispatchers.Default) {
                listOfCompanies.mapIndexed { i, company ->
                    ConfigureViewModelAdapter.calculateCostAndDeltaCost(
                        company,
                        listOfTickers[i],
                        "c12e84v48v6oi252mgog"
                    )
                }
            }
            _stocksList.postValue(list)
        }
    }

    fun addToFavoriteList(company: Company) {
        val currentList = favoriteList.value ?: mutableListOf()
        currentList.add(company)
        _favoriteList.postValue(currentList)
        _stocksList.postValue(stocksList.value)
    }

    fun removeFromFavoriteList(company: Company) {
        val currentList = favoriteList.value ?: return
        currentList.remove(company)
        _favoriteList.postValue(currentList)
        _stocksList.postValue(stocksList.value)
    }
}
