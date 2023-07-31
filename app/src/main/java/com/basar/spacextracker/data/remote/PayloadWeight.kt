package com.basar.spacextracker.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayloadWeight(
    @SerializedName("id")
    val id: String?,
    @SerializedName("kg")
    val kg: Int?,
    @SerializedName("lb")
    val lb: Int?,
    @SerializedName("name")
    val name: String?
) : Parcelable