package com.invest.tickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invest.tickerapp.R
import com.invest.tickerapp.model.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigureViewModel : ViewModel() {
    private val _favoriteList = MutableLiveData<MutableList<Company>>()
    val favoriteList: LiveData<MutableList<Company>>
        get() = _favoriteList

    private val _stocksList = MutableLiveData<List<Company>>()
    val stocksList: LiveData<List<Company>>
        get() = _stocksList

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val list = listOf(
                Company(
                    "Yandex, LLC",
                    "YNDX",
                    "4 764,6 ₽",
                    "+55 ₽ (1,15%)",
                    R.drawable.logo_yndx,
                    true
                ),
                Company(
                    "Apple Inc.",
                    "AAPL",
                    "$131.93",
                    "+$0.12 (1,15%)",
                    R.drawable.logo_aapl,
                    true
                ),
                Company(
                    "Alphabet Class A",
                    "GOOGL",
                    "$1 825",
                    "+$0.12 (1,15%)",
                    R.drawable.logo_googl,
                    false
                ),
                Company(
                    "Amazon.com",
                    "AMZN",

                    "$3 204",
                    "-$0.12 (1,15%)",
                    R.drawable.logo_amzn,
                    false
                ),
                Company(
                    "Bank od America Corp",
                    "BAC",
                    "$3 204",
                    "+$0.12 (1,15%)",
                    R.drawable.logo_bac,
                    false
                ),
                Company(
                    "Microsoft Corporation",
                    "MSFT",
                    "$3 204",
                    "+$0.12 (1,15%)",
                    R.drawable.logo_msft,
                    false
                )
            )
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