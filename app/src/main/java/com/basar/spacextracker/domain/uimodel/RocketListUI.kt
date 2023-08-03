package com.basar.spacextracker.domain.uimodel

import android.os.Parcelable
import com.basar.spacextracker.data.local.model.Converters
import com.basar.spacextracker.data.local.model.Rocket
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketListUI(
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
) : Parcelable {
    fun toRocket() = Rocket(
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
}