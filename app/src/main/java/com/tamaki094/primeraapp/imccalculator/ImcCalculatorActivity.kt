package com.tamaki094.primeraapp.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import com.tamaki094.primeraapp.R
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var txtHeigth:TextView
    private lateinit var rsHeigth:RangeSlider

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponent()
        initListener()
        initUI()

    }

    private fun initUI() {
        onClickView()
    }

    private fun initListener() {
        viewFemale.setOnClickListener {
            onClickView()
        }
        viewMale.setOnClickListener {
            onClickView()
        }

        rsHeigth.addOnChangeListener{ _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            txtHeigth.text = "$result cm"
        }
    }

    private fun onClickView() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

        viewMale.setCardBackgroundColor(getBackGroundColor((isMaleSelected)))
        viewFemale.setCardBackgroundColor(getBackGroundColor((isFemaleSelected)))
    }

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



    private fun initComponent() {
        viewMale = findViewById<CardView>(R.id.viewMale)
        viewFemale = findViewById<CardView>(R.id.viewFemale)
        txtHeigth = findViewById<TextView>(R.id.txtViewHeigth)
        rsHeigth = findViewById<RangeSlider>(R.id.rsHeigth)

    }


}