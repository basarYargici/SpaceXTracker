package com.basar.spacextracker.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.basar.spacextracker.databinding.FragmentFavouritesBinding
import com.basar.spacextracker.ui.rockets.RocketListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var rocketAdapter: RocketListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initVM()
        initRV()
        setObservers()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rocketList.collect { list ->
                    rocketAdapter.submitList(list?.toMutableList())
                }
            }
        }
    }

    private fun initRV() {
        binding.rvRockets.apply {
            rocketAdapter = RocketListAdapter()
            with(rocketAdapter) {
                itemClickListener = {
                    timber.log.Timber.d(it.toString())
                }
                favItemClickListener = {
                    viewModel.deleteRocket(it.id)
                }
                adapter = this
            }
        }
    }
}