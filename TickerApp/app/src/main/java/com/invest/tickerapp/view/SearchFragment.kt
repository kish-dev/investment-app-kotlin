package com.invest.tickerapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.invest.tickerapp.databinding.FragmentSearchBinding

class SearchFragment : UpdateListFragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        initRecycler(binding.searchRecycler)
        return binding.root
    }

}