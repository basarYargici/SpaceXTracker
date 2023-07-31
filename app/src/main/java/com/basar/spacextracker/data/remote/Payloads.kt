package com.basar.spacextracker.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payloads(
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairing?,
    @SerializedName("option_1")
    val option1: String?
) : Parcelable