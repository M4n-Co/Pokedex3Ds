package com.example.pokedex3ds.pokedex.domain

import javax.inject.Inject

class GetPokemonListUC @Inject constructor(
    private val repository: PokedexRepository
) {
    suspend operator fun invoke() = repository.getPokemonList()
}