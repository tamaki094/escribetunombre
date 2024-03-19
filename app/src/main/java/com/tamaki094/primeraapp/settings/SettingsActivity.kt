package com.tamaki094.primeraapp.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binging: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binging.root)
    }
}