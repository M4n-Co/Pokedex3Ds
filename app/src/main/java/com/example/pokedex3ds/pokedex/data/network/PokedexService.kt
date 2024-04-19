package com.example.pokedex3ds.pokedex.data.network

import com.example.pokedex3ds.pokedex.data.network.model.PokedexModel
import retrofit2.Response
import retrofit2.http.GET

interface PokedexService {
    @GET("pokemon?limit=151&offset=0")
    suspend fun getPokemonList(): Response<PokedexModel>
}