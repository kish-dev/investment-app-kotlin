package com.invest.tickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.network.Repository
import kotlinx.coroutines.*

class ConfigureViewModel() : ViewModel() {
    private val _favoriteList = MutableLiveData<MutableList<Company>>()
    val favoriteList: LiveData<MutableList<Company>>
        get() = _favoriteList

    private val _stocksList = MutableLiveData<List<Company>>()
    val stocksList: LiveData<List<Company>>
        get() = _stocksList

    init {

        viewModelScope.launch(Dispatchers.Main) {

            var list: MutableList<Company> = mutableListOf()
            for (i in Company.ListOfCompaniesLoader.listOfCompanies.indices) {
                var company: Company? = null
                CoroutineScope(Dispatchers.Default).launch {
                    company = (Repository.calculateCostAndDeltaCost(
                        Company.ListOfCompaniesLoader.listOfCompanies[i],
                        Company.ListOfCompaniesLoader.listOfTickers[i],
                        "c12e84v48v6oi252mgog"
                    )
                            )
                }
                delay(1000L)
                list.add(company!!)
            }

            //TODO init favoriteList too
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