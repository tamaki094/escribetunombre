package com.tamaki094.primeraapp.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.firstapp.todoapp.ToDoActivity
import com.tamaki094.primeraapp.horoscapp.ui.home.HoroscAppMain
import com.tamaki094.primeraapp.imccalculator.ImcCalculatorActivity
import com.tamaki094.primeraapp.settings.SettingsActivity
import com.tamaki094.primeraapp.superheroapp.SuperHeroListActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnImcApp = findViewById<Button>(R.id.btnImcApp)
        val btnToDo = findViewById<Button>(R.id.btnToDo)
        val btnSuperHero = findViewById<Button>(R.id.btnSuperHero)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnHoroscApp = findViewById<Button>(R.id.btnHoroscApp)
        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnImcApp.setOnClickListener { navigateToImcApp() }
        btnToDo.setOnClickListener { navigateToToDoApp() }
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }
        btnSettings.setOnClickListener { navigateToSettings() }
        btnHoroscApp.setOnClickListener { navigateToHoroscApp() }

    }

    private fun navigateToSuperHeroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToToDoApp() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
    }

    fun navigateToImcApp() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSaludApp()
    {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    fun navigateToSettings()
    {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHoroscApp() {
        val intent = Intent(this, HoroscAppMain::class.java)
        startActivity(intent)
    }
}
