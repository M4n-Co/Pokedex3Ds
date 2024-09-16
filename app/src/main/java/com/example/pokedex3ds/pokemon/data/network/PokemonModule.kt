package com.example.pokedex3ds.pokemon.data.network

import com.example.pokedex3ds.pokemon.data.PokemonRepositoryImpl
import com.example.pokedex3ds.pokemon.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Singleton
    @Provides
    fun provideDetalles(retrofit: Retrofit): PokemonService{
        return retrofit.create(PokemonService::class.java)
    }

    @Singleton
    @Provides
    fun providesPokemonService(service: PokemonService) : PokemonRepository{
        return PokemonRepositoryImpl(service)
    }

}