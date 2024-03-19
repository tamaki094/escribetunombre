package com.tamaki094.primeraapp.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tamaki094.primeraapp.R
import java.util.Collections.emptyList


class SuperHeroAdapter(var superheroList:List<SuperHeroItemResponse> = emptyList(), private val onItemSelected:(String) -> Unit):RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superheroList:List<SuperHeroItemResponse>){
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superheroList[position], onItemSelected)
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}