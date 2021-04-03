package com.invest.tickerapp.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.invest.tickerapp.IClickCompany
import com.invest.tickerapp.R
import com.invest.tickerapp.databinding.FragmentConfigureBinding
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.data.ConfigureViewModelAdapter_Factory
import com.invest.tickerapp.model.di.ViewModelFactory
import com.invest.tickerapp.viewmodel.ConfigureViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfigureFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val stocksFragment = StocksFragment()

    private val favoriteFragment = FavoriteFragment()

    private val searchFragment = SearchFragment()

    private lateinit var binding: FragmentConfigureBinding

    private lateinit var viewModel: ConfigureViewModel

    private lateinit var mySearchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = viewModels<ConfigureViewModel> { viewModelFactory }.value
        binding = FragmentConfigureBinding.inflate(inflater, container, false)
        mySearchView = binding.searchView


        mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchQueryString: String?): Boolean {
                searchQueryString?.let { viewModel.search(searchQueryString) }
                return true
            }
        }
        )

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
            return when (position) {
                0 -> resources.getText(com.invest.tickerapp.R.string.stocks_tab)
                1 -> resources.getText(com.invest.tickerapp.R.string.favorite_tab)
                else -> null
            }
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
        searchFragment.onClickAction = stocksFragment.onClickAction
    }

    private fun ConfigureViewModel.initObservers() {
        favoriteList.observe(viewLifecycleOwner) {
            favoriteFragment.update(it)
        }
        stocksList.observe(viewLifecycleOwner) {
            stocksFragment.update(it)
        }
        searchList.observe(viewLifecycleOwner) {
            searchFragment.update(it)
        }
    }
}
