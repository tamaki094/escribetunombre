package com.tamaki094.primeraapp.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var  txtResult:TextView
    private lateinit var  txtIMC:TextView
    private lateinit var  txtDescription:TextView
    private lateinit var  btnReCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result = intent.extras?.getDouble(IMC_KEY)?: -1.0

        initComponents()
        initUI(result)
        initListeners()
    }

    /**
     * Inicializa listeners
     */
    private fun initListeners() {
        btnReCalculate.setOnClickListener{ onBackPressed()}
    }

    /**
     * Inicializa Interfaz Grafica
     */
    private fun initUI(result: Double) {

        txtIMC.text = result.toString()

        when(result)
        {
            in 0.00..18.50 -> { //bajo peso
                txtResult.text = getString(R.string.title_bajo_peso)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                txtDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> { //peso normal
                txtResult.text = getString(R.string.title_peso_normal)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                txtDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> { //sobrepeso
                txtResult.text  = getString(R.string.title_sobrepeso)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                txtDescription.text  = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 -> { //obesidad
                txtResult.text = getString(R.string.title_obesidad)
                txtResult.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                txtDescription.text = getString(R.string.description_obesidad)
            }
            else ->{ //error
                txtIMC.text = getString(R.string.error)
                txtResult.text = getString(R.string.error)
                txtDescription.text = getString(R.string.error)
            }
        }
    }

    /**
     * Inicializa componentes
     */
    private fun initComponents() {
        txtIMC = findViewById(R.id.txtIMC)
        txtResult = findViewById(R.id.txtResult)
        txtDescription = findViewById(R.id.txtDescription)
        btnReCalculate = findViewById(R.id.btnRecalculate)
    }
}