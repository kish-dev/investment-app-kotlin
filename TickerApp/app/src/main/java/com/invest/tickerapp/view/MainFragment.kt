package com.invest.tickerapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.invest.tickerapp.IClickCompany
import com.invest.tickerapp.R
import com.invest.tickerapp.databinding.FragmentMainBinding
import com.invest.tickerapp.model.data.Company
import com.invest.tickerapp.model.di.ViewModelFactory
import com.invest.tickerapp.viewmodel.ConfigureViewModel
import com.invest.tickerapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val searchFragment = SearchFragment()

    private lateinit var binding: FragmentMainBinding

    private lateinit var searchEditText: EditText

    private lateinit var viewModel: SearchViewModel

    private lateinit var mainNavController: NavController

    private lateinit var searchIcon: ImageView
    private lateinit var deleteIcon: ImageView
    private lateinit var backIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModels<SearchViewModel> { viewModelFactory }.value
        binding = FragmentMainBinding.inflate(inflater, container, false)

        searchEditText = binding.searchView
        searchIcon = binding.searchIcon
        deleteIcon = binding.deleteIcon
        backIcon = binding.backIcon

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_main__nav_host_fragment) as NavHostFragment
        mainNavController = navHostFragment.navController

        searchEditText.apply {
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    searchEditText.hint = ""
                    navigateToSearch(searchEditText.text.isEmpty())
                    searchIcon.visibility = View.GONE
                    backIcon.visibility = View.VISIBLE
                }
            }

            doOnTextChanged { text, start, before, count ->
                if (text.isNullOrEmpty()) {
                    deleteIcon.visibility = View.GONE
                } else {
                    mainNavController.navigate(R.id.searchFragment)
                    deleteIcon.visibility = View.VISIBLE
                }
                viewModel.search(text.toString())

            }
        }

        deleteIcon.setOnClickListener {
            searchEditText.text.clear()
        }

        backIcon.setOnClickListener {
            searchEditText.hint = getString(R.string.search)
            searchEditText.text.clear()
            searchEditText.clearFocus()
            mainNavController.navigate(R.id.configureFragment)

            searchIcon.visibility = View.VISIBLE
            deleteIcon.visibility = View.GONE
            backIcon.visibility = View.GONE
        }

        if (searchEditText.hasFocus()) {
            requireView().findNavController()
                .navigate(R.id.searchFragment)
        }

        return binding.root
    }

    private fun navigateToSearch(isEmptyField: Boolean) {
        if (!isEmptyField) {
            mainNavController.navigate(R.id.searchFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.initView()
        initObservers()
    }

    private fun FragmentMainBinding.initView() {
        searchFragment.onClickAction = object : IClickCompany {
            override fun onCheck(company: Company) {
                viewModel.addToFavoriteList(company)
            }

            override fun onUncheck(company: Company) {
                viewModel.removeFromFavoriteList(company)
            }
        }
    }

    private fun initObservers() {
        viewModel.searchList.observe(viewLifecycleOwner) {
            searchFragment.update(it)
        }
    }


}

