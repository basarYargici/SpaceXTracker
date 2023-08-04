package com.basar.spacextracker.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class EmptyDiffCallBack<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = true

    override fun areContentsTheSame(oldItem: T, newItem: T) = true
}

class FalseDiffCallback<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = false
    override fun areContentsTheSame(oldItem: T, newItem: T) = false
}
