package com.basar.spacextracker.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketDetailUI(
    var id: String,
    var imageUrl: List<String>? = null,
    var name: String? = null,
    var isActive: Boolean? = null,
    var firstFlight: String? = null,
    var country: String? = null,
    var company: String? = null,
    var wikipedia: String? = null,
    var description: String? = null,
    var height: String? = null,
    var weight: String? = null,
    var engineCount: String? = null,
    var isFavourite: Boolean = false
) : Parcelable