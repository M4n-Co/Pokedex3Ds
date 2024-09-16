package com.example.pokedex3ds.pokemon.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex3ds.R
import com.example.pokedex3ds.databinding.FragmentPokemonBinding
import com.example.pokedex3ds.pokemon.data.network.model.PokemonDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private lateinit var mBinding : FragmentPokemonBinding

    private val args : PokemonFragmentArgs by navArgs()
    private val pokemonName : String get() = args.pokemonName
    private val pokemonViewModel : PokemonViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPokemonBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        getPokeInfo()
        initObservers()
    }

    private fun initObservers() {

        pokemonViewModel.isLoading.observe(viewLifecycleOwner){
            mBinding.pbLoading.isVisible = it
            mBinding.mcvTypeOne.isVisible = !it
        }

        pokemonViewModel.pokemonDetails.observe(viewLifecycleOwner){pokemonDetails ->
            if (pokemonDetails != null){
                setPokeInfo(pokemonDetails)
            }
        }
    }

    private  fun setPokeInfo(pokemonDetails: PokemonDetails){
        val nomAndName = "N. ${pokemonDetails.id} ${pokemonDetails.name}"
        mBinding.tvNomAndName.text = nomAndName

        val height = getCorrectHeightAndWidth(pokemonDetails.height.toString()) + " m"
        mBinding.tvHeight.text = height

        val width = getCorrectHeightAndWidth(pokemonDetails.weight.toString()) + " kg"
        mBinding.tvWidth.text = width

        val typeOne = pokemonDetails.types[0].type.name
        mBinding.tvTypeOne.text = typeOne
        mBinding.mcvTypeOne.setCardBackgroundColor(getPokemonTypeColor(typeOne))

        if(pokemonDetails.types.size == 2){
            mBinding.mcvTypeTwo.isVisible = true
            val typeTwo = pokemonDetails.types[1].type.name
            mBinding.tvTypeTwo.text = typeTwo
            mBinding.mcvTypeTwo.setCardBackgroundColor(getPokemonTypeColor(typeTwo))
        }

        Glide.with(mBinding.ivPokemonPhoto.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonDetails.id}.png")
            .into(mBinding.ivPokemonPhoto)
    }

    private fun getCorrectHeightAndWidth(height: String): String{
        val correctHeight = if (height.length == 1){
            "0.$height"
        }else{
            StringBuilder(height).insert(height.length -1, ".").toString()
        }

        return correctHeight
    }

    private fun getPokeInfo() {
        pokemonViewModel.getPokeInfo(pokemonName)
    }

    private fun getPokemonTypeColor(type: String): Int {
        return when (type.lowercase()) {
            "normal" -> ContextCompat.getColor(requireContext(), R.color.normal_type)
            "fire" -> ContextCompat.getColor(requireContext(), R.color.fire_type)
            "water" -> ContextCompat.getColor(requireContext(), R.color.water_type)
            "electric" -> ContextCompat.getColor(requireContext(), R.color.electric_type)
            "grass" -> ContextCompat.getColor(requireContext(), R.color.grass_type)
            "ice" -> ContextCompat.getColor(requireContext(), R.color.ice_type)
            "fighting" -> ContextCompat.getColor(requireContext(), R.color.fighting_type)
            "poison" -> ContextCompat.getColor(requireContext(), R.color.poison_type)
            "ground" -> ContextCompat.getColor(requireContext(), R.color.ground_type)
            "flying" -> ContextCompat.getColor(requireContext(), R.color.flying_type)
            "psychic" -> ContextCompat.getColor(requireContext(), R.color.psychic_type)
            "bug" -> ContextCompat.getColor(requireContext(), R.color.bug_type)
            "rock" -> ContextCompat.getColor(requireContext(), R.color.rock_type)
            "ghost" -> ContextCompat.getColor(requireContext(), R.color.ghost_type)
            "dragon" -> ContextCompat.getColor(requireContext(), R.color.dragon_type)
            else -> ContextCompat.getColor(requireContext(), R.color.normal_type) // Default to normal type color
        }
    }

}