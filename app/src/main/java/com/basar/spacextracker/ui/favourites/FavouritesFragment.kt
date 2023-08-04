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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basar.spacextracker.databinding.FragmentFavouritesBinding
import com.basar.spacextracker.ext.visibleIf
import com.basar.spacextracker.ui.rockets.RocketListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var rocketAdapter: RocketListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        initAdapter()
        initRV()
        viewModel.getFavouriteRocketList()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    setShimmerLoadingVisibility(state.isLoading)
                    setRocketList(state)
                    setNotFoundVisibility(state)
                }
            }
        }
    }

    private fun setShimmerLoadingVisibility(isLoading: Boolean) {
        binding.shimmer.visibleIf(isLoading)
    }

    private fun setRocketList(state: FavouriteUIState) {
        binding.rvRockets.visibleIf(!state.isLoading)
        rocketAdapter.submitList(state.items.toMutableList())
    }

    private fun setNotFoundVisibility(state: FavouriteUIState) {
        if (!state.isLoading) {
            binding.llNotFound.visibleIf(state.items.isEmpty())
        }
    }

    private fun initAdapter() {
        rocketAdapter = RocketListAdapter().apply {
            itemClickListener = {
                Timber.d(it.toString())
            }
            favItemClickListener = {
                viewModel.deleteRocket(it.id)
            }
        }
    }

    private fun initRV() {
        binding.rvRockets.apply {
            adapter = rocketAdapter
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}