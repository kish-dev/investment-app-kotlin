package com.invest.tickerapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.invest.tickerapp.databinding.ActivityMainBinding
import com.invest.tickerapp.model.di.ViewModelFactory
import com.invest.tickerapp.view.ConfigureFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame,
            ConfigureFragment()
        ).commit()
        setContentView(binding.root)
    }
}
