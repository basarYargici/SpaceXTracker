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
        viewModel.initVM(args.id)
        setObservers()
        initRV()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rocket.collect {
                    it?.let { rocket ->
                        with(binding) {
                            tvDescription.text = rocket.description
                            tvName.text = rocket.name
                            tvHeight.text = rocket.height
                            tvWeight.text = rocket.weight
                            tvEngine.text = rocket.engineCount
                        }
                        rocket.imageUrl?.let { url ->
                            detailsAdapter.submitList(url)
                        }
                    }
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