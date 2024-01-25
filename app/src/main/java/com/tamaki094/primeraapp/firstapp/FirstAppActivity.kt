package com.tamaki094.primeraapp.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.tamaki094.primeraapp.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val txtName = findViewById<AppCompatEditText>(R.id.etName)
        var nombre :String= ""

        btnStart.setOnClickListener{
            nombre = txtName.text.toString()

            if (!nombre.isEmpty())
            {
                Log.i("Mensaje", "Escrito: ${txtName.text.toString()}")

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
            }
            else
            {
                Log.i("Mensaje", "No puede ir vacio")
            }


        }

        println("Hola")
    }
}