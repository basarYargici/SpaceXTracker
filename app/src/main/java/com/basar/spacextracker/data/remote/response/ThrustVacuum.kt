package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int?,
    @SerializedName("lbf")
    val lbf: Int?
) : Parcelable