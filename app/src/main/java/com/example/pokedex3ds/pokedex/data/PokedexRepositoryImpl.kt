package com.example.pokedex3ds.pokedex.data

import com.example.pokedex3ds.pokedex.data.network.PokedexService
import com.example.pokedex3ds.pokedex.data.network.model.PokedexModel
import com.example.pokedex3ds.pokedex.domain.PokedexRepository
import retrofit2.Response
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor (
    private val pokedexService: PokedexService
) : PokedexRepository {
    override suspend fun getPokemonList(): Response<PokedexModel> {
        return pokedexService.getPokemonList()
    }
}