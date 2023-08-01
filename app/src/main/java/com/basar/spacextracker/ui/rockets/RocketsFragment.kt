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
import com.basar.spacextracker.data.local.model.ImageUrl
import com.basar.spacextracker.data.local.model.Rocket
import com.basar.spacextracker.data.local.model.RocketWithImages
import com.basar.spacextracker.databinding.FragmentRocketsBinding
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
    }

    private fun initRV() {
        binding.rvRockets.apply {
            rocketAdapter = RocketListAdapter()
            with(rocketAdapter) {
                itemClickListener = {
                    Timber.d(it.toString())
                }
                favItemClickListener = {
                    val rocketWithImages = RocketWithImages(rocket = Rocket(
                        id = 0,
                        rocketName = it.name ?: "",
                        country = it.country ?: "",
                        company = it.company ?: "",
                        isFavorite = !it.isFavourite,
                        description = it.description ?: ""
                    ), imageUrls = it.imageUrl?.map { url ->
                        ImageUrl(id = 0, url = url)
                    } ?: emptyList())

                    viewModel.addRocket(rocketWithImages)
                    Timber.d(it.toString())
                }
                adapter = this
            }
        }
    }
}