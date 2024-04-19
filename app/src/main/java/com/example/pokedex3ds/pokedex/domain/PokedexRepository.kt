package com.example.pokedex3ds.pokedex.domain

import com.example.pokedex3ds.pokedex.data.network.model.PokedexModel
import retrofit2.Response

interface PokedexRepository {
    suspend fun getPokemonList() : Response<PokedexModel>
}