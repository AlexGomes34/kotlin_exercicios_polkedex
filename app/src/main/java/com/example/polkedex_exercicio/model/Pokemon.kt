package com.example.polkedex_exercicio.model

import com.google.gson.annotations.SerializedName

data class Pokemons (
    val id: Int,
    val name: String,
    val sprites: com.google.gson.JsonObject // Mapeia como objeto puro
)

