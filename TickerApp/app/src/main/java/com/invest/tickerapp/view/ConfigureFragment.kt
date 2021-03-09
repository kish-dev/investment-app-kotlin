package com.invest.tickerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.invest.tickerapp.IClickCompany
import com.invest.tickerapp.databinding.FragmentConfigureBinding
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.viewmodel.ConfigureViewModel


class ConfigureFragment : Fragment() {

    private val stocksFragment = StocksFragment()
    private val favoriteFragment = FavoriteFragment()

    private lateinit var binding: FragmentConfigureBinding

    private lateinit var viewModel: ConfigureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ConfigureViewModel::class.java)
        binding = FragmentConfigureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.initView()
        viewModel.initObservers()
    }

    inner class NewPagerAdapter : FragmentPagerAdapter(
        childFragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> stocksFragment
                else -> favoriteFragment
            }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return resources.getText(com.invest.tickerapp.R.string.stocks_tab)
                1 -> return resources.getText(com.invest.tickerapp.R.string.favorite_tab)
            }
            return null
        }
    }

    private fun FragmentConfigureBinding.initView() {
        pager.adapter = NewPagerAdapter()
        tabs.setupWithViewPager(pager)
        stocksFragment.onClickAction = object : IClickCompany {
            override fun onCheck(company: Company) {
                viewModel.addToFavoriteList(company)
            }

            override fun onUncheck(company: Company) {
                viewModel.removeFromFavoriteList(company)
            }
        }
        favoriteFragment.onClickAction = stocksFragment.onClickAction
    }

    private fun ConfigureViewModel.initObservers() {
        favoriteList.observe(viewLifecycleOwner) {
            favoriteFragment.update(it)
        }
        stocksList.observe(viewLifecycleOwner) {
            stocksFragment.update(it)
        }
    }
}
