package com.tamaki094.primeraapp.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tamaki094.primeraapp.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = findViewById<TextView>(R.id.txtUser)
        val name: String = intent.extras?.getString("nombre").orEmpty()
        result.text = "Hola ${name}"
    }
}