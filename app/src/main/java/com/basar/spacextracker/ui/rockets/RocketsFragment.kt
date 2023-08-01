package com.basar.spacextracker.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.basar.spacextracker.databinding.FragmentRocketsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : Fragment() {
    private lateinit var binding: FragmentRocketsBinding
    private val viewModel: RocketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initVM()
    }
}