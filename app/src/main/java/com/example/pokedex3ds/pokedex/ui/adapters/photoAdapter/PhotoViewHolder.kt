package com.example.pokedex3ds.pokedex.ui.adapters.photoAdapter

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex3ds.databinding.ItemPokemonPhotoBinding

class PhotoViewHolder ( private  val mBinding : ItemPokemonPhotoBinding) : RecyclerView.ViewHolder(mBinding.root) {

    fun bind(url : String){
        Glide.with(mBinding.ivPhoto.context)
            .load(url)
            .into(mBinding.ivPhoto)
    }

}