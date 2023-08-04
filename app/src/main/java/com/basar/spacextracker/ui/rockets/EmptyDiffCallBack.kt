package com.basar.spacextracker.ui.rockets

import androidx.recyclerview.widget.DiffUtil

class EmptyDiffCallBack<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = true

    override fun areContentsTheSame(oldItem: T, newItem: T) = true
}

class FalseDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = false
    override fun areContentsTheSame(oldItem: T, newItem: T) = false
}
