package com.example.polkedex_exercicio.service

import android.telecom.Call
import com.example.polkedex_exercicio.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    //GET do pokemon por id/nome
    @GET("pokemon/{value}")
    suspend fun getPokemonDetails(
        @Path("value") value: String
    ): retrofit2.Call<List<Pokemon>>
}