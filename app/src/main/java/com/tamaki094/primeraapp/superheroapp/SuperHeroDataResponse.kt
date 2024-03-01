package com.tamaki094.primeraapp.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response:String,
    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
)
