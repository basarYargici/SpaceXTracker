package com.basar.spacextracker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.basar.spacextracker.databinding.FragmentDashboardBinding
import com.basar.spacextracker.ui.favourites.FavouritesFragment
import com.basar.spacextracker.ui.rockets.RocketsFragment
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        binding.viewPager.adapter = AdapterPagerMainTab(this)

        val tabNames = arrayListOf(
            "Dashboard", "Favourites"
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}

class AdapterPagerMainTab(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var items = arrayListOf(RocketsFragment(), FavouritesFragment())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int) = items[position]
}