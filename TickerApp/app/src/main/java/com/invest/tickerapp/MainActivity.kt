package com.invest.tickerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.invest.tickerapp.databinding.ActivityMainBinding
import com.invest.tickerapp.model.di.ViewModelFactory
import com.invest.tickerapp.view.ConfigureFragment
import com.invest.tickerapp.viewmodel.ConfigureViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    //TODO right init for chosen
    //TODO init in app or SaveInstanceState (saving List stocks
    //TODO Glide (ImageView)
    //TODO Search in ConfigureFragment, update list
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