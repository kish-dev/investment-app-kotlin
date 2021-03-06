package com.invest.tickerapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

import com.invest.tickerapp.databinding.ActivityMainBinding
import com.invest.tickerapp.view.ConfigureFragment
//Todo to write Search in ConfigureFragment, SearchFragment from StocksList, diffUtil update Recycler

class MainActivity : AppCompatActivity() {

    //TODO right init for chosen
    //TODO init in app or SaveInstanceState (saving List stocks
    //TODO Glide (ImageView)
    //TODO Search in ConfigureFragment, update list
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame, ConfigureFragment()).commit()
        setContentView(binding.root)
    }
}