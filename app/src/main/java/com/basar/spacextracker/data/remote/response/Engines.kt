package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: Int? = null,
    @SerializedName("isp")
    val isp: Isp? = null,
    @SerializedName("layout")
    val layout: String? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("propellant_1")
    val propellant1: String? = null,
    @SerializedName("propellant_2")
    val propellant2: String? = null,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevel? = null,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double? = null,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuum? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("version")
    val version: String? = null
) : Parcelable