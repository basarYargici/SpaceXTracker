package com.basar.spacextracker.domain.uimodel

import android.os.Parcelable
import com.basar.spacextracker.data.local.model.Converters
import com.basar.spacextracker.data.remote.response.Rocket
import kotlinx.parcelize.Parcelize
import com.basar.spacextracker.data.local.model.Rocket as LocalRocket

@Parcelize
data class RocketUIItem(
    var id: String,
    var imageUrl: List<String>? = null,
    var name: String? = null,
    var country: String? = null,
    var company: String? = null,
    var wikipedia: String? = null,
    var description: String? = null,
    var height: String? = null,
    var weight: String? = null,
    var engineCount: String? = null,
    var isFavourite: Boolean = false
) : Parcelable

// TODO: move this extensions
fun LocalRocket.toRocketUIItem() = RocketUIItem(
    id,
    Converters.fromString(images),
    name,
    country,
    company,
    wikipedia,
    description,
    height,
    weight,
    engineCount
)

fun Rocket.toRocketUIItem() = RocketUIItem(
    id ?: "0",
    flickrImages,
    name,
    country,
    company,
    wikipedia,
    description,
    height?.meters.toString(),
    mass?.kg.toString(),
    engines?.number.toString()
)

fun RocketUIItem.toRocket() = LocalRocket(
    id = id,
    name = name ?: "",
    country = country ?: "",
    company = company ?: "",
    isFavorite = isFavourite,
    description = description ?: "",
    images = Converters.fromArrayList(imageUrl),
    wikipedia = wikipedia,
    height = height,
    weight = weight,
    engineCount = engineCount
)