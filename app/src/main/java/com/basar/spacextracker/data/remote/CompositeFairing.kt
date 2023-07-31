package com.basar.spacextracker.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: Diameter?,
    @SerializedName("height")
    val height: Height?
) : Parcelable