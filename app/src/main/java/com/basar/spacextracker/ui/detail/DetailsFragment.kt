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
import com.basar.spacextracker.databinding.FragmentDetailsBinding
import com.basar.spacextracker.ext.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var detailsAdapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
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
            rocket.apply {
                with(binding) {
                    tvDescription.text = description
                    tvName.text = name
                    tvHeight.text = "$height meters"
                    tvWeight.text = "$weight kilograms"
                    tvEngine.text = "$engineCount engines"
                }
                imageUrl?.let { url ->
                    detailsAdapter.submitList(url)
                }
            }
        }
    }

    private fun initRV() {
        binding.rvImages.apply {
            detailsAdapter = DetailsAdapter()
            adapter = detailsAdapter
        }
    }
}