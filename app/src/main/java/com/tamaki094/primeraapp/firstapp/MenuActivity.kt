package com.tamaki094.primeraapp.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.firstapp.todoapp.ToDoActivity
import com.tamaki094.primeraapp.imccalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnImcApp = findViewById<Button>(R.id.btnImcApp)
        val btnToDo = findViewById<Button>(R.id.btnToDo)
        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnImcApp.setOnClickListener { navigateToImcApp() }
        btnToDo.setOnClickListener { navigateToToDoApp() }

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
}