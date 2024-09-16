package com.example.pokedex3ds.pokemon.domain

import javax.inject.Inject

class GetPokemonInfoUC @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonName : String) = pokemonRepository.getPokemonInfo(pokemonName)
}