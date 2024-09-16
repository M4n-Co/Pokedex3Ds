package com.example.pokedex3ds.control.ui

import android.icu.text.Transliterator.Position
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor() : ViewModel() {

    private val _rvScroll = MutableLiveData<Int>()
    val pokemosVM = MutableLiveData<List<PokemonList>>()
    val rvScroll : LiveData<Int> get() = _rvScroll

    fun scrollUp(position: Int){

        _rvScroll.value = position

    }
    fun scrollDown(position: Int){

        _rvScroll.value = position

    }

}