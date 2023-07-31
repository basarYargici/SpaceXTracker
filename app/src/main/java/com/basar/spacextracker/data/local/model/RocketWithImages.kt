package com.basar.spacextracker.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class RocketWithImages(
    @Embedded val rocket: Rocket, @Relation(
        parentColumn = "rocket_id", entityColumn = "url_id"
    ) val imageUrls: List<ImageUrl>
)