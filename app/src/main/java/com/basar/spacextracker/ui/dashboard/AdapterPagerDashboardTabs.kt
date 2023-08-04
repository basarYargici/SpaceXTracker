package com.basar.spacextracker.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.basar.spacextracker.ui.favourites.FavouritesFragment
import com.basar.spacextracker.ui.rockets.RocketsFragment

class AdapterPagerDashboardTabs(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var items = arrayListOf(RocketsFragment(), FavouritesFragment())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int) = items[position]
}