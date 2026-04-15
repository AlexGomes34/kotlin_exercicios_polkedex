package com.example.polkedex_exercicio.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val baseUrl = "http://pokeapi.co/api/v2/"

    private val retrofitFactory = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    fun getPokemonService(): PokemonService {
        return retrofitFactory.create(PokemonService::class.java)
    }
}