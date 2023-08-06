package com.basar.spacextracker.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rocket(
    @SerializedName("active")
    val active: Boolean? = null,
    @SerializedName("boosters")
    val boosters: Int? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("diameter")
    val diameter: Diameter? = null,
    @SerializedName("engines")
    val engines: Engines? = null,
    @SerializedName("first_flight")
    val firstFlight: String? = null,
    @SerializedName("first_stage")
    val firstStage: FirstStage? = null,
    @SerializedName("flickr_images")
    val flickrImages: List<String>? = null,
    @SerializedName("height")
    val height: Height? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("landing_legs")
    val landingLegs: LandingLegs? = null,
    @SerializedName("mass")
    val mass: Mass? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeight>? = null,
    @SerializedName("second_stage")
    val secondStage: SecondStage? = null,
    @SerializedName("stages")
    val stages: Int? = null,
    @SerializedName("success_rate_pct")
    val successRatePct: Int? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("wikipedia")
    val wikipedia: String? = null
) : Parcelable