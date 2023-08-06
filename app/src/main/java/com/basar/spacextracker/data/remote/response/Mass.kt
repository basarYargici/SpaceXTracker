package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mass(
    @SerializedName("kg")
    val kg: Int? = null,
    @SerializedName("lb")
    val lb: Int? = null
) : Parcelable