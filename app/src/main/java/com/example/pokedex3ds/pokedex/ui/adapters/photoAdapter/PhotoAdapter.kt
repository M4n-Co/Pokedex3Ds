package com.example.pokedex3ds.pokedex.ui.adapters.photoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex3ds.databinding.ItemPokemonPhotoBinding

class PhotoAdapter ( private val urls : List<String>) : RecyclerView.Adapter<PhotoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPokemonPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int = 151

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item : String = urls[position]
        holder.bind(item)
    }
}