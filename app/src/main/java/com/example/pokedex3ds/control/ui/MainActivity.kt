package com.example.pokedex3ds.control.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.pokedex3ds.R
import com.example.pokedex3ds.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private val mViewModel : ControlViewModel by viewModels()
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(mBinding.main.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()
    }

    private fun initUI() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        mBinding.ibtnUp.setOnClickListener {
            position - 1
            mViewModel.scrollUp(position)
        }
        mBinding.ibtnDown.setOnClickListener {
            position + 1
            mViewModel.scrollDown(position)
        }

        mBinding.btnA.setOnClickListener{
            findNavController(mBinding.fcPokedex.id).navigate(R.id.action_pokedexFragment_to_pokemonFragment)
        }

        mBinding.btnB.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

}