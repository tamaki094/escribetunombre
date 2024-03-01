package com.tamaki094.primeraapp.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tamaki094.primeraapp.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse)
    {
        binding.txtSuperHeroName.text = superHeroItemResponse.name
    }
}