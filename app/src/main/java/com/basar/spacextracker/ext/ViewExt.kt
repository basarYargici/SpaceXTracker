package com.basar.spacextracker.ext

import android.view.View
import android.view.View.VISIBLE

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) VISIBLE else View.GONE
}

fun View.setVisible() {
    visibility = VISIBLE
}

fun View.setInvisible() {
    visibility = VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}