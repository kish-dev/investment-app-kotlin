package com.invest.tickerapp

import com.invest.tickerapp.model.Company

interface IClickCompany {
    fun onCheck(company: Company)
    fun onUncheck(company: Company)
}