package com.basar.spacextracker.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketListUI(
    var imageUrl: String? = null,
    var name: String? = null,
    var isActive: Boolean? = null,
    var firstFlight: String? = null,
    var country: String? = null,
    var company: String? = null,
    var wikipedia: String? = null,
    var description: String? = null
) : Parcelable