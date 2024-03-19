package com.tamaki094.primeraapp.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/3845005655781728/search/{name}")
    suspend fun getSuperHeroes(@Path("name")superHeroName:String): Response<SuperHeroDataResponse>
    @GET("/api/3845005655781728/{id}")
    suspend fun getSuperHeroById(@Path("id")superHeroId:String): Response<SuperHeroDetailResponse>
}