package com.example.polkedex_exercicio.service

import android.telecom.Call
import com.example.polkedex_exercicio.model.AllPokemons
import com.example.polkedex_exercicio.model.Pokemons
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    //GET do pokemon por id/nome
    @GET("pokemon/{value}")
    suspend fun getPokemonDetails(
        @Path("value") value: String
    ): Pokemons

    //GET todos os pokemons
    @GET("pokemon/?offset=0&limit=100")
    suspend fun getAllPokemons(): AllPokemons
}