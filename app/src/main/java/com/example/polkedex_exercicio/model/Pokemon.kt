package com.example.polkedex_exercicio.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
    val id: Int,
    val name: String,
    @SerializedName("front_default")val imagem: String
)
