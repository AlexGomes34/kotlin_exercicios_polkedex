package com.example.polkedex_exercicio.model

import com.google.gson.annotations.SerializedName

data class AllPokemons (
    @SerializedName("results") val result: List<com.google.gson.JsonObject>
)