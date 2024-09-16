package com.example.pokedex3ds.pokemon.data.network.model

data class PokemonDetails(
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val types: List<Type>,
    val weight: Int
)