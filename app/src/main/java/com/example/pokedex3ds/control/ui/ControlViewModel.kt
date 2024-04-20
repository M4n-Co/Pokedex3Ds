package com.example.pokedex3ds.control.ui

import android.icu.text.Transliterator.Position
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ControlViewModel @Inject constructor() : ViewModel() {

    private val _rvScroll = MutableLiveData<Int>()
    val rvScroll : LiveData<Int> = _rvScroll

    fun scrollUp(position: Int){
        if (position >= 0){
            _rvScroll.value = position
        }
    }
    fun scrollDown(position: Int){
        if (position <= 151){
            _rvScroll.value = position
        }
    }

}