package com.example.pokedex3ds.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex3ds.R
import com.example.pokedex3ds.databinding.FragmentPokedexBinding
import com.example.pokedex3ds.databinding.FragmentPokemonBinding


class PokemonFragment : Fragment() {

    private lateinit var mBinding : FragmentPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPokemonBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

}