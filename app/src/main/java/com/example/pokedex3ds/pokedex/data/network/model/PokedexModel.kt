package com.example.pokedex3ds.pokedex.data.network.model

import com.google.gson.annotations.SerializedName

data class PokedexModel(
    @SerializedName("results") val pokemonList: List<PokemonList>
)