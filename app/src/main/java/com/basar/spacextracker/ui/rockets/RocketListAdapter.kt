package com.basar.spacextracker.ui.rockets

import com.basar.spacextracker.R
import com.basar.spacextracker.databinding.ItemRocketBinding
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.ui.adapter.DataBindingAdapter
import com.basar.spacextracker.ui.adapter.DataBindingViewHolder
import com.basar.spacextracker.ui.adapter.FalseDiffCallback

class RocketListAdapter : DataBindingAdapter<RocketUIItem>(FalseDiffCallback()) {

    var favItemClickListener: ((RocketUIItem) -> Unit)? = null

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_rocket

    override fun onBindViewHolder(holder: DataBindingViewHolder<RocketUIItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItem(position)

        if (holder.binding is ItemRocketBinding) {
            with(holder.binding.ivFav) {
                isSelected = item.isFavourite
                setOnClickListener {
                    with(item) {
                        isFavourite = isFavourite.not()
                        it.isSelected = isFavourite
                        favItemClickListener?.invoke(item)
                    }
                }
            }
        }
    }
}

