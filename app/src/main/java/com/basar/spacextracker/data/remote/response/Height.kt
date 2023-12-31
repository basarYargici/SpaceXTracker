package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Height(
    @SerializedName("feet")
    val feet: Double? = null,
    @SerializedName("meters")
    val meters: Double? = null
) : Parcelable