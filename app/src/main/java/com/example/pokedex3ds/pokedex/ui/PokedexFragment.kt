package com.example.pokedex3ds.pokedex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex3ds.control.ui.ControlViewModel
import com.example.pokedex3ds.databinding.FragmentPokedexBinding
import com.example.pokedex3ds.pokedex.data.network.model.PokemonList
import com.example.pokedex3ds.pokedex.ui.adapters.PokedexAdapter.PokedexAdapter
import com.example.pokedex3ds.pokedex.ui.adapters.photoAdapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexFragment : Fragment() {

    private lateinit var mBinding : FragmentPokedexBinding
    private val pokedexViewModel : PokedexViewModel by viewModels()
    private val controlViewModel : ControlViewModel by viewModels()
    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var photoAdapter : PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentPokedexBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        getPokemonList()
        initObserversPokedex()
        initObserversControl()
    }

    private fun initObserversControl() {
        controlViewModel.rvScroll.observe(viewLifecycleOwner){
            mBinding.rvNumberAndName.scrollToPosition(it)
        }
    }

    private fun initObserversPokedex() {
        pokedexViewModel.isLoading.observe(viewLifecycleOwner){
            mBinding.pbLoading.isVisible = it
        }

        pokedexViewModel.pokemosVM.observe(viewLifecycleOwner){pokemoList ->
            if (pokemoList != null){
                initRecyclerView(pokemoList)
                initRecyclerViewPhotos()
            }
        }

        pokedexViewModel.error.observe(viewLifecycleOwner){error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG ).show()
        }
    }

    private fun initRecyclerViewPhotos() {
        val urls = mutableListOf<String>()

        for (i in 1 .. 151){
            urls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$i.png")
        }

        photoAdapter = PhotoAdapter(urls)

        mBinding.rvPokemonPhoto.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvPokemonPhoto.adapter = photoAdapter
    }

    private fun initRecyclerView(pokemoList: List<PokemonList>) {
        pokedexAdapter = PokedexAdapter(pokemoList)

        mBinding.rvNumberAndName.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvNumberAndName.adapter = pokedexAdapter
    }

    fun getPokemonList(){
        pokedexViewModel.getPokemonList()
    }

}