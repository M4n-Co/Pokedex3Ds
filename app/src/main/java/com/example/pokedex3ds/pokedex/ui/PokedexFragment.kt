package com.example.pokedex3ds.pokedex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val controlViewModel : ControlViewModel by activityViewModels()
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
    }

    private fun initObserversControl() {
        controlViewModel.rvScroll.observe(viewLifecycleOwner){
            //mBinding.rvNumberAndName.smoothScrollToPosition(it)
            //mBinding.rvPokemonPhoto.smoothScrollToPosition(it)

            pokedexAdapter.positionSelected = it
            photoAdapter.positionSelected = it

            centerItem(it, mBinding.rvNumberAndName)
            centerItem(it, mBinding.rvPokemonPhoto)
        }
    }

    /*fun centerItemPhoto(position: Int) {
        val layoutManager = mBinding.rvPokemonPhoto.layoutManager as LinearLayoutManager

        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (position < firstVisibleItemPosition || position > lastVisibleItemPosition) {
            // El ítem no está visible, desplaza el RecyclerView
            mBinding.rvPokemonPhoto.smoothScrollToPosition(position)
        } else {
            // El ítem está visible, calcula el desplazamiento para centrarlo
            val view = layoutManager.findViewByPosition(position)
            if (view != null) {
                // Obtener la posición del ítem en relación al RecyclerView
                val itemTop = view.top
                val itemBottom = view.bottom

                // Obtener el tamaño del RecyclerView
                val recyclerViewHeight = mBinding.rvPokemonPhoto.height

                // Calcular la mitad del tamaño del RecyclerView y del ítem
                val recyclerViewCenter = recyclerViewHeight / 2
                val itemCenter = (itemTop + itemBottom) / 2

                // Calcular el desplazamiento necesario para centrar el ítem
                val offset = itemCenter - recyclerViewCenter

                // Realizar el scroll para centrar el ítem
                mBinding.rvPokemonPhoto.smoothScrollBy(0, offset)
            }
        }

        mBinding.rvPokemonPhoto.findViewHolderForAdapterPosition(position)?.itemView?.performClick()
    }*/

    private fun centerItem(position: Int, rv : RecyclerView) {
        val layoutManager = rv.layoutManager as LinearLayoutManager

        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (position < firstVisibleItemPosition || position > lastVisibleItemPosition) {
            // El ítem no está visible, desplaza el RecyclerView
            rv.smoothScrollToPosition(position)
        } else {
            // El ítem está visible, calcula el desplazamiento para centrarlo
            val view = layoutManager.findViewByPosition(position)
            if (view != null) {
                // Obtener la posición del ítem en relación al RecyclerView
                val itemTop = view.top
                val itemBottom = view.bottom

                // Obtener el tamaño del RecyclerView
                val recyclerViewHeight = rv.height

                // Calcular la mitad del tamaño del RecyclerView y del ítem
                val recyclerViewCenter = recyclerViewHeight / 2
                val itemCenter = (itemTop + itemBottom) / 2

                // Calcular el desplazamiento necesario para centrar el ítem
                val offset = itemCenter - recyclerViewCenter

                // Realizar el scroll para centrar el ítem
                rv.smoothScrollBy(0, offset)
            }
        }
        rv.findViewHolderForAdapterPosition(position)?.itemView?.performClick()
    }

    private fun initObserversPokedex() {
        pokedexViewModel.isLoading.observe(viewLifecycleOwner){
            mBinding.pbLoading.isVisible = it
        }

        pokedexViewModel.pokemosVM.observe(viewLifecycleOwner){pokemoList ->
            if (pokemoList != null){
                controlViewModel.pokemosVM.value = pokemoList
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


        initObserversControl()
    }

    private fun initRecyclerView(pokemoList: List<PokemonList>) {
        pokedexAdapter = PokedexAdapter(pokemoList)

        mBinding.rvNumberAndName.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvNumberAndName.adapter = pokedexAdapter
    }

    private fun getPokemonList(){
        pokedexViewModel.getPokemonList()
    }

}