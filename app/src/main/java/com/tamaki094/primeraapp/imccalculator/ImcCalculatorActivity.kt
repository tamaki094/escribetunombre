package com.tamaki094.primeraapp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.firstapp.ResultActivity
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {


    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var txtHeigth:TextView
    private lateinit var rsHeigth:RangeSlider
    private lateinit var btnSubstractWeight:FloatingActionButton
    private lateinit var btnPlusWeight:FloatingActionButton
    private lateinit var txtWeight:TextView
    private lateinit var btnSubstractAge:FloatingActionButton
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var txtAge:TextView
    private lateinit var btnCalculate:Button

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false

    private var currentWeigth: Int = 70
    private var currentHeigth: Int = 171
    private var currentAge: Int = 29

    companion object{
        val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponent()
        initListener()
        initUI()
    }

    /**
     * Inicializa Componentes
     */
    private fun initComponent() {
        viewMale = findViewById<CardView>(R.id.viewMale)
        viewFemale = findViewById<CardView>(R.id.viewFemale)
        txtHeigth = findViewById<TextView>(R.id.txtViewHeigth)
        rsHeigth = findViewById<RangeSlider>(R.id.rsHeigth)
        btnSubstractWeight = findViewById<FloatingActionButton>(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById<FloatingActionButton>(R.id.btnPlusWeight)
        txtWeight = findViewById<TextView>(R.id.txtWeight)
        btnSubstractAge = findViewById<FloatingActionButton>(R.id.btnSubtractAge)
        btnPlusAge = findViewById<FloatingActionButton>(R.id.btnPlusAge)
        txtAge = findViewById<TextView>(R.id.txtAge)
        btnCalculate = findViewById<Button>(R.id.btnCalculate)
    }


    /**
     * Inicializa Interfaz Grafica
     */
    private fun initUI() {
        onClickView()
        setWeight()
        setAge()
    }

    /**
     * Inicializa Listeners
     */
    private fun initListener() {
        viewFemale.setOnClickListener {
            onClickView()
        }
        viewMale.setOnClickListener {
            onClickView()
        }

        rsHeigth.addOnChangeListener{ _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeigth = df.format(value).toInt()
            txtHeigth.text = "$currentHeigth cm"
        }

        btnSubstractWeight.setOnClickListener {
            currentWeigth -=  1
            setWeight()
        }

        btnPlusWeight.setOnClickListener {
            currentWeigth += 1
            setWeight()
        }

        btnSubstractAge.setOnClickListener {
            currentAge -=  1
            setAge()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)

        }
    }

    /**
     * Ir a la pantalla de resultado
     * @param result El IMC
     */
    private fun navigateToResult(result: Double) {
        val intent =  Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    /**
     * Calcula indice de masa corporal
     */
    private fun calculateIMC() : Double {
        var df = DecimalFormat("#.##")
        var imc = currentWeigth / ((currentHeigth.toDouble()/100) * (currentHeigth.toDouble() / 100))
        var result = df.format(imc).toDouble()
        return df.format(imc).toDouble()
    }

    /**
     * Cambia la edad del textview
     */
    private fun setAge() {
        txtAge.text = currentAge.toString()
    }

    /**
     * Cambia el peso del textview
     */
    private fun setWeight()
    {
        txtWeight.text = currentWeigth.toString()
    }

    /**
     * Se ejecuta al hacer click en un CardView de SEXO
     */
    private fun onClickView() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

        viewMale.setCardBackgroundColor(getBackGroundColor((isMaleSelected)))
        viewFemale.setCardBackgroundColor(getBackGroundColor((isFemaleSelected)))
    }

    /**
     * Obtiene un Color
     * @property isSelectedComponent Esta Seleccionado
     * @return Devuelve un entero que es el Color
     */
    private fun getBackGroundColor(isSelectedComponent: Boolean) : Int
    {
        val colorReference = if(isSelectedComponent)
        {
            R.color.rojo_cafeblack
        }
        else
        {
            R.color.rojo_cocacola
        }

        return ContextCompat.getColor(this, colorReference)
    }




}