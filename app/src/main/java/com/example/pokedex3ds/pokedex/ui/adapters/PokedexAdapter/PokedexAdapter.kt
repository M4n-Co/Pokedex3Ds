package com.example.pokedex3ds.pokedex.ui.adapters.PokedexAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex3ds.databinding.ItemPokemonNameBinding
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList

class PokedexAdapter (private val pokemonList: List<PokemonList>) : RecyclerView.Adapter<PokedexViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val binding = ItemPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokedexViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val item : PokemonList = pokemonList[position]
        holder.bind(item)
    }
}