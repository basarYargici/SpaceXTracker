package com.basar.spacextracker.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String) {
    this.load(url) {
        crossfade(true)
        allowHardware(false)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: List<String>) {
    this.load(url.firstOrNull()) {
        crossfade(true)
        allowHardware(false)
    }
}
