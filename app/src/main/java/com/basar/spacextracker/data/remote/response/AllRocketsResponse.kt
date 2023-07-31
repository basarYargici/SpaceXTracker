package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllRocketsResponse(
    val rockets: List<Rocket>?
) : Parcelable