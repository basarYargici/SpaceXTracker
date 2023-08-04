package com.basar.spacextracker.ui.rockets

import com.basar.spacextracker.R
import com.basar.spacextracker.databinding.ItemRocketBinding
import com.basar.spacextracker.domain.uimodel.RocketUIItem

class RocketListAdapter : DataBindingAdapter<RocketUIItem>(FalseDiffCallback()) {

    var favItemClickListener: ((RocketUIItem) -> Unit)? = null

    override fun getItemLayoutId(viewType: Int): Int = R.layout.item_rocket

    override fun onBindViewHolder(holder: DataBindingViewHolder<RocketUIItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = getItem(position)
        if (holder.binding is ItemRocketBinding) {
            holder.binding.ivFav.setOnClickListener {
                with(item) {
                    isFavourite = isFavourite.not()
                    it.isSelected = isFavourite
                    favItemClickListener?.invoke(item)
                }
            }
        }
    }
}

