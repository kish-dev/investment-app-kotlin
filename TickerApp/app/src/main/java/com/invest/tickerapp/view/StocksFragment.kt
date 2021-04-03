package com.invest.tickerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.invest.tickerapp.databinding.FragmentStocksBinding



class StocksFragment : UpdateListFragment() {

    private lateinit var binding: FragmentStocksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStocksBinding.inflate(inflater, container, false)
        initRecycler(binding.stocksRecycler)
        return binding.root
    }

}
