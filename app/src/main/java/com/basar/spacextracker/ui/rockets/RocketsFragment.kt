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
import com.basar.spacextracker.databinding.FragmentRocketsBinding
import com.basar.spacextracker.ext.visibleIf
import com.basar.spacextracker.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RocketsFragment : Fragment() {
    private lateinit var binding: FragmentRocketsBinding
    private val viewModel: RocketsViewModel by viewModels()
    private lateinit var rocketAdapter: RocketListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketsBinding.inflate(layoutInflater)
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

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showLoading.collect {
                    binding.shimmer.visibleIf(it)
                    binding.rvRockets.visibleIf(!it)
                }
            }
        }
    }

    private fun initRV() {
        binding.rvRockets.apply {
            rocketAdapter = RocketListAdapter()
            with(rocketAdapter) {
                itemClickListener = {
                    Timber.d(it.toString())
                    findNavController().navigate(
                        DashboardFragmentDirections.toDetailsFragment(
                            it.id
                        )
                    )
                }
                favItemClickListener = {
                    with(it) {
                        if (!isFavourite) {
                            viewModel.deleteRocket(id)
                        } else {
                            viewModel.addRocket(it.toRocket())
                        }
                    }
                    Timber.d(it.toString())
                }
                adapter = this
            }
        }
    }
}