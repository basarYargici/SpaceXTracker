package com.basar.spacextracker.ui.dashboard.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basar.spacextracker.databinding.FragmentFavouritesBinding
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ext.visibleIf
import com.basar.spacextracker.ui.dashboard.DashboardFragmentDirections
import com.basar.spacextracker.ui.dashboard.RocketSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavouritesViewModel by viewModels()
    private val sharedVM: RocketSharedViewModel by activityViewModels()
    private lateinit var favouritesAdapter: FavouritesAdapter

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
        favouritesAdapter.submitList(state.items)
    }

    private fun setNotFoundVisibility(state: FavouriteUIState) {
        if (!state.isLoading) {
            binding.llNotFound.root.visibleIf(state.items.isEmpty())
        }
    }

    private fun initAdapter() {
        favouritesAdapter = FavouritesAdapter().apply {
            itemClickListener = {
                navigateToDetails(it)
            }
            favItemClickListener = {
                deleteRocket(it)
            }
        }
    }

    private fun navigateToDetails(it: RocketUIItem) = findNavController().navigate(
        DashboardFragmentDirections.toDetailsFragment(it.id)
    )

    private fun deleteRocket(it: RocketUIItem) = viewLifecycleOwner.lifecycleScope.launch {
        sharedVM.deletedItemId.emit(it.id)
        viewModel.deleteRocket(it.id)
    }

    private fun initRV() {
        binding.rvRockets.apply {
            adapter = favouritesAdapter
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