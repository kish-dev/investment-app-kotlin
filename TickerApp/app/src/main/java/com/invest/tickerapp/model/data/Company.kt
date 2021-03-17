package com.invest.tickerapp.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.invest.tickerapp.R

@Entity
data class Company(
    @PrimaryKey
    var companyName: String,

    @ColumnInfo(name = "company_name")
    var companyTicker: String,

    @ColumnInfo(name = "cost")
    var cost: String,

    @ColumnInfo(name = "delta_cost")
    var deltaCost: String,

    @ColumnInfo(name = "logo_id_image")
    var logoIdImage: Int,

    @ColumnInfo(name = "star")
    var star: Boolean
) {
    object ListOfCompaniesLoader {
        val listOfCompanies: MutableList<Company> = mutableListOf(
            Company(
                "Yandex, LLC",
                "YNDX",
                "4 764,6 ₽",
                "+55 ₽ (1,15%)",
                R.drawable.logo_yndx,
                false
            ),
            Company(
                "Apple Inc.",
                "AAPL",
                "$131.93",
                "+$0.12 (1,15%)",
                R.drawable.logo_aapl,
                false
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
            ),
            Company(
                "Tesla Motors",
                "TSLA",
                "$3 204",
                "+$0.12 (1,15%)",
                R.drawable.logo_tsla,
                false
            ),
            Company(
                "Mastercard",
                "MA",
                "$3 204",
                "+$0.12 (1,15%)",
                R.drawable.logo_ma,
                false
            )
        )
        val listOfTickers: List<String> =  listOfCompanies.map{it.companyTicker}

    }

}
