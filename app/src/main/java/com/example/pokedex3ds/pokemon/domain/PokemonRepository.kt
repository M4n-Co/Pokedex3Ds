package com.example.pokedex3ds.pokemon.domain

import com.example.pokedex3ds.pokemon.data.network.model.PokemonDetails
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemonInfo(pokemonName : String) : Response<PokemonDetails>
}