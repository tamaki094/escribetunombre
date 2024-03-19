package com.tamaki094.primeraapp.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.databinding.ActivityDetailSuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                val superheroDetail =
                    getRetrofit().create(ApiService::class.java).getSuperHeroById(id)

                if(superheroDetail.body() != null){
                    runOnUiThread { createUI(superheroDetail.body()!!) }
                    superheroDetail.body()
                }
            }
            catch (e: Exception)
            {
                Log.i("mensaje_error", e.toString())
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Log.i("detalle:", superHero.toString())
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        binding.tvFullName.text = superHero.biography.fullName
        binding.tvPublisher.text = superHero.biography.publisher


        prepareStats(superHero.powerstats)
    }


    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.vwCombat, powerstats.combat)
        updateHeight(binding.vwSpeed, powerstats.speed)
        updateHeight(binding.vwStrength, powerstats.strength)
        updateHeight(binding.vwIntelligence, powerstats.intelligence)
        updateHeight(binding.vwPower, powerstats.power)
        updateHeight(binding.vwDurability, powerstats.durability)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun updateHeight(view: View, stat: String ){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }
}