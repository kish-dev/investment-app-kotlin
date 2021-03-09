package com.invest.tickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfigureViewModel() : ViewModel() {
    private val _favoriteList = MutableLiveData<MutableList<Company>>()
    val favoriteList: LiveData<MutableList<Company>>
        get() = _favoriteList

    private val _stocksList = MutableLiveData<List<Company>>()
    val stocksList: LiveData<List<Company>>
        get() = _stocksList

    init {

        viewModelScope.launch(Dispatchers.Main) {

            val list: MutableList<Company>

            val Yandex = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[0],
                    Company.ListOfCompaniesLoader.listOfTickers[0],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Apple = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[1],
                    Company.ListOfCompaniesLoader.listOfTickers[1],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Google = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[2],
                    Company.ListOfCompaniesLoader.listOfTickers[2],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Amazon = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[3],
                    Company.ListOfCompaniesLoader.listOfTickers[3],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Bac = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[4],
                    Company.ListOfCompaniesLoader.listOfTickers[4],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Microsoft = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[5],
                    Company.ListOfCompaniesLoader.listOfTickers[5],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Tesla = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[6],
                    Company.ListOfCompaniesLoader.listOfTickers[6],
                    "c12e84v48v6oi252mgog"
                )
            }
            val Ma = withContext(Dispatchers.Default) {
                Repository.calculateCostAndDeltaCost(
                    Company.ListOfCompaniesLoader.listOfCompanies[7],
                    Company.ListOfCompaniesLoader.listOfTickers[7],
                    "c12e84v48v6oi252mgog"
                )
            }
            list = mutableListOf(
                Yandex,
                Apple,
                Google,
                Amazon,
                Bac,
                Microsoft,
                Tesla,
                Ma
            )
            if (_stocksList.value.isNullOrEmpty()) {

                _stocksList.postValue(list)
                //TODO init favoriteList too

            } else {
                _stocksList.postValue(stocksList.value)
                _favoriteList.postValue(favoriteList.value)
            }
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