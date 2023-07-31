package com.basar.spacextracker.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllRocketsResponse(
    val rockets: List<Rocket>?
) : Parcelable