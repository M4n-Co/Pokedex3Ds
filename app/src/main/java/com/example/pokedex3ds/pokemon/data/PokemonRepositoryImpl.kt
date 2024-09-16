package com.example.pokedex3ds.pokemon.data

import com.example.pokedex3ds.pokemon.data.network.PokemonService
import com.example.pokedex3ds.pokemon.data.network.model.PokemonDetails
import com.example.pokedex3ds.pokemon.domain.PokemonRepository
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
)  : PokemonRepository{
    override suspend fun getPokemonInfo(pokemonName : String): Response<PokemonDetails> {
        return pokemonService.getPokemonInfo("pokemon/$pokemonName")
    }
}