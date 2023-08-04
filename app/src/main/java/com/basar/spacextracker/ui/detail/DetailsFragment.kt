package com.basar.spacextracker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.basar.spacextracker.R
import com.basar.spacextracker.databinding.FragmentDetailsBinding
import com.basar.spacextracker.ext.visibleIf
import com.basar.spacextracker.ui.DefaultRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private var detailsAdapter = DefaultRecyclerAdapter<String>(R.layout.item_rocket_image)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        initRV()
        viewModel.getRocketList(args.id)
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    setShimmerLoadingVisibility(state.isLoading)
                    setRocket(state)
                }
            }
        }
    }

    private fun setShimmerLoadingVisibility(isLoading: Boolean) {
        binding.shimmer.visibleIf(isLoading)
    }

    private fun setRocket(state: DetailsUIState) {
        binding.group.visibleIf(!state.isLoading)

        state.item?.let { rocket ->
            binding.model = rocket
            rocket.imageUrl?.let { url ->
                detailsAdapter.submitList(url)
            }
        }
    }

    private fun initRV() {
        binding.rvImages.adapter = detailsAdapter
    }
}

