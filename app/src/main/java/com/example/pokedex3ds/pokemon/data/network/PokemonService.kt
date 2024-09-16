package com.example.pokedex3ds.pokemon.data.network

import com.example.pokedex3ds.pokemon.data.network.model.PokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonService {
    @GET
    suspend fun getPokemonInfo(@Url url : String ) : Response<PokemonDetails>
}