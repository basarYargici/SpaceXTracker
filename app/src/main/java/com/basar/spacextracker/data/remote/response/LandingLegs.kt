package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class LandingLegs(
    @SerializedName("material")
    val material: String?,
    @SerializedName("number")
    val number: Int?
) : Parcelable