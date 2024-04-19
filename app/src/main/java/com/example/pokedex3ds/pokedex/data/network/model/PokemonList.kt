package com.example.pokedex3ds.pokedex.data.network.model

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)