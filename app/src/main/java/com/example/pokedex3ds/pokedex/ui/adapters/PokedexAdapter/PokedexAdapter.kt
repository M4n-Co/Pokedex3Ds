package com.example.pokedex3ds.pokedex.ui.adapters.PokedexAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex3ds.R
import com.example.pokedex3ds.databinding.ItemPokemonNameBinding
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList

class PokedexAdapter (private val pokemonList: List<PokemonList>
) : RecyclerView.Adapter<PokedexViewHolder>(){

    var positionSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val binding = ItemPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokedexViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val item : PokemonList = pokemonList[position]
        val ivSelectorName = holder.itemView.findViewById<ImageView>(R.id.ivSelectorName)

        holder.itemView.setOnClickListener {
            positionSelected = position
            notifyDataSetChanged()
        }

        ivSelectorName.isVisible = positionSelected == position

        holder.bind(item)
    }

}