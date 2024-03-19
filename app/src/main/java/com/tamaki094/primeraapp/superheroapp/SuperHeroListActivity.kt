package com.tamaki094.primeraapp.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tamaki094.primeraapp.R
import com.tamaki094.primeraapp.databinding.ActivitySuperHeroListBinding
import com.tamaki094.primeraapp.superheroapp.DetailSuperHeroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        adapter = SuperHeroAdapter{superHeroId ->  navigateToDetail(superHeroId)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(query: String){
        Log.i("mensaje", "buscando ${query}")
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("mensaje", "consultando en api")
            try
            {
                val myResponse = retrofit.create(ApiService::class.java).getSuperHeroes(query)
                Log.i("mensaje", "se obtubo clase response")
                if(myResponse.isSuccessful){
                    val response:SuperHeroDataResponse? = myResponse.body()

                    if(response != null){
                        Log.i("mensaje", response.toString())
                        runOnUiThread {
                            adapter.updateList(response.superheroes)
                            binding.progressBar.isVisible = false
                        }

                    }
                    Log.i("mensaje", "funciona :D")
                }
                else{
                    Log.i("mensaje", "no funciona :(")
                }
            }
            catch (e: Exception)
            {
                Log.i("mensaje_error", e.toString())
            }
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this,DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}