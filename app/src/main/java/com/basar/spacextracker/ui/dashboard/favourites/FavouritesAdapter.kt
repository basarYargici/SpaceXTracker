package com.basar.spacextracker.ui.dashboard.favourites

import com.basar.spacextracker.R
import com.basar.spacextracker.databinding.ItemRocketBinding
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ui.adapter.DataBindingAdapter
import com.basar.spacextracker.ui.adapter.DataBindingViewHolder
import com.basar.spacextracker.ui.adapter.FalseDiffCallback

class FavouritesAdapter : DataBindingAdapter<RocketUIItem>(FalseDiffCallback()) {

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_rocket

    override fun onBindViewHolder(holder: DataBindingViewHolder<RocketUIItem>, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        if (holder.binding is ItemRocketBinding) {
            with(holder.binding.ivFav) {
                isSelected = true
                setOnClickListener {
                    isSelected = false
                    itemClickListener?.invoke(item)
                }
            }
        }
    }
}