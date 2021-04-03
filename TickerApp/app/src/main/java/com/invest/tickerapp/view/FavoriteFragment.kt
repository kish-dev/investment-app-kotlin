package com.invest.tickerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.invest.tickerapp.databinding.FragmentFavoriteBinding



class FavoriteFragment : UpdateListFragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        initRecycler(binding.favoriteRecycler)
        return binding.root
    }
}
