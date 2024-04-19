package com.example.pokedex3ds.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList
import com.example.pokedex3ds.pokedex.domain.GetPokemonListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokemonListUC: GetPokemonListUC
) : ViewModel() {

    val pokemosVM = MutableLiveData<List<PokemonList>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getPokemonList(){
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            val response = getPokemonListUC()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){

                    pokemosVM.postValue(response.body()?.pokemonList)
                    isLoading.postValue(false)

                }else{
                    isLoading.postValue(false)
                    error.postValue(response.message())
                }
            }

        }
    }

}