package com.basar.spacextracker.ui.rockets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.basar.spacextracker.databinding.ItemRocketBinding
import com.basar.spacextracker.domain.uimodel.RocketListUI

class RocketListAdapter : ListAdapter<RocketListUI, RocketListAdapter.RocketListViewHolder>(DiffCallback()) {
    var itemClickListener: ((RocketListUI?) -> Unit)? = null
    var favItemClickListener: ((RocketListUI?) -> Unit)? = null

    private class DiffCallback : DiffUtil.ItemCallback<RocketListUI>() {
        override fun areItemsTheSame(oldItem: RocketListUI, newItem: RocketListUI) = false
        override fun areContentsTheSame(oldItem: RocketListUI, newItem: RocketListUI) = oldItem == newItem
    }

    inner class RocketListViewHolder(private val binding: ItemRocketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val rocket = getItem(position)
            binding.apply {
                with(rocket) {
                    ivRocket.load(imageUrl?.first())
                    tvName.text = name
                    tvHeight.text = "$height meters"
                    tvWeight.text = "$weight kilograms"
                    tvCountry.text = country
                    tvEngine.text = "$engineCount engines"
                    materialCV.setOnClickListener {
                        itemClickListener?.invoke(rocket)
                    }
                    ivFav.setOnClickListener {
                        isFavourite = isFavourite.not()
                        it.isSelected = isFavourite
                        favItemClickListener?.invoke(rocket)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketListViewHolder {
        return RocketListViewHolder(
            ItemRocketBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RocketListViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = currentList.size
}