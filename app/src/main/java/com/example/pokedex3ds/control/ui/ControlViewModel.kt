package com.example.pokedex3ds.control.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor() : ViewModel() {

    val rvScroll = MutableLiveData<Int> (1)
    val moveOrBAck = MutableLiveData<Int> ()
    val test = MutableLiveData<Unit> ()

    fun scrollUp(){
        viewModelScope.launch {
            if (rvScroll.value!! == 1){
                rvScroll.value = rvScroll.value?.plus(1)
            }
        }
    }
    fun scrollDown(){
        viewModelScope.launch {
            if (rvScroll.value!! >= 1){
                rvScroll.value = rvScroll.value?.minus(1)
            }
        }
    }

    fun goToPokemonDetails(){
        viewModelScope.launch {
            moveOrBAck.value = 2
        }
    }

    fun back(){
        viewModelScope.launch {
            moveOrBAck.value = 1
        }
    }

}