package com.example.pokedex3ds.pokemon.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex3ds.pokemon.data.network.model.PokemonDetails
import com.example.pokedex3ds.pokemon.domain.GetPokemonInfoUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonInfoUC: GetPokemonInfoUC
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val pokemonDetails = MutableLiveData<PokemonDetails>()
    val error = MutableLiveData<String>()

    fun getPokeInfo(pokemonName : String){
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            val response = getPokemonInfoUC(pokemonName)

            withContext(Dispatchers.Main){
                if (response.isSuccessful){

                    pokemonDetails.postValue(response.body())
                    isLoading.postValue(false)

                }else{
                    isLoading.postValue(false)
                    error.postValue(response.message())
                }
            }
        }
    }
}