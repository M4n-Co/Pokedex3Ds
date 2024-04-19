package com.example.pokedex3ds.pokedex.data.network

import com.example.pokedex3ds.pokedex.data.PokedexRepositoryImpl
import com.example.pokedex3ds.pokedex.domain.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {

    @Provides
    fun getPokemonList(retrofit: Retrofit):PokedexService{
        return retrofit.create(PokedexService::class.java)
    }

    @Provides
    fun providesPokedexService(service: PokedexService) : PokedexRepository{
        return PokedexRepositoryImpl(service)
    }
}