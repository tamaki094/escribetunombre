package com.tamaki094.primeraapp.horoscapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.databinding.ActivityHoroscAppMainBinding

class HoroscAppMain : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscAppMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHoroscAppMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}