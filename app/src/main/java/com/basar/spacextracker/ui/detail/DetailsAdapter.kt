package com.basar.spacextracker.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.basar.spacextracker.databinding.ItemRocketImageBinding
import com.basar.spacextracker.ui.detail.DetailsAdapter.DetailsViewHolder

class DetailsAdapter : ListAdapter<String, DetailsViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String) = false
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    inner class DetailsViewHolder(private val binding: ItemRocketImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val rocket = getItem(position)
            binding.apply {
                ivRocket.load(rocket)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DetailsViewHolder {
        return DetailsViewHolder(
            ItemRocketImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = currentList.size
}