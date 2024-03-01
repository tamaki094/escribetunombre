package com.tamaki094.primeraapp.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamaki094.primeraapp.R

class SuperHeroAdapter(var superheroList:List<SuperHeroItemResponse> = emptyList()):RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superheroList:List<SuperHeroItemResponse>){
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superheroList[position])
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}