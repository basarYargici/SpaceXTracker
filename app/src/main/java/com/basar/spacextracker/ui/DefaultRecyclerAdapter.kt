package com.basar.spacextracker.ui

import androidx.recyclerview.widget.DiffUtil

class DefaultRecyclerAdapter<T>(
    private val layoutId: Int,
    diffCallBack: DiffUtil.ItemCallback<T> = EmptyDiffCallBack()
) : DataBindingAdapter<T>(diffCallBack){
    override fun getItemLayoutId(viewType: Int): Int = layoutId
}