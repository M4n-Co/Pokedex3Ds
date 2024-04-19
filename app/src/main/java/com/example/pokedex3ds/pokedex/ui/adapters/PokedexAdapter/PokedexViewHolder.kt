package com.example.pokedex3ds.pokedex.ui.adapters.PokedexAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex3ds.databinding.ItemPokemonNameBinding
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList

class PokedexViewHolder (private val mBinding : ItemPokemonNameBinding): RecyclerView.ViewHolder(mBinding.root) {

    fun bind(item : PokemonList){
        val namePokemon = item.name
        val number = getNumero(item.url)

        mBinding.tvPokemonName.text = "$number $namePokemon"

    }

    private fun getNumero(url:String): String {
        val partes = url.split("/")
        val numero = partes[partes.size-2].toInt()
        val result = if(numero < 10){
            "00$numero"
        } else if(numero < 100){
            "0$numero"
        }else{
            "$numero"
        }
        return result
    }
}