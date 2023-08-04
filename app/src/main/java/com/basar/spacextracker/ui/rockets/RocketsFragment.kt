package com.basar.spacextracker.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basar.spacextracker.databinding.FragmentRocketsBinding
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ext.visibleIf
import com.basar.spacextracker.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RocketsFragment : Fragment() {

    private var _binding: FragmentRocketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RocketsViewModel by viewModels()
    private lateinit var rocketAdapter: RocketListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        initAdapter()
        initRV()
        viewModel.getRocketList()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    setShimmerLoadingVisibility(state)
                    setRocketList(state)
                }
            }
        }
    }

    private fun setShimmerLoadingVisibility(state: RocketsUIState) {
        binding.shimmer.visibleIf(state.isLoading)
    }

    private fun setRocketList(state: RocketsUIState) {
        binding.rvRockets.visibleIf(!state.isLoading)
        rocketAdapter.submitList(state.items.toMutableList())
    }

    private fun initAdapter() {
        rocketAdapter = RocketListAdapter().apply {
            itemClickListener = {
                navigateToDetails(it)
            }
            favItemClickListener = {
                with(it) {
                    if (!isFavourite) {
                        viewModel.deleteRocket(id)
                    } else {
                        viewModel.addRocket(it.toRocket())
                    }
                }
            }
        }
    }

    private fun navigateToDetails(it: RocketUIItem) {
        findNavController().navigate(
            DashboardFragmentDirections.toDetailsFragment(it.id)
        )
    }

    private fun initRV() {
        binding.rvRockets.apply {
            adapter = rocketAdapter
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
        }
    }
}