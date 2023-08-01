package com.basar.spacextracker.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "image_url")
@Parcelize
data class ImageUrl(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "url_id") val id: Int,
    @ColumnInfo(name = "url") val url: String?,
): Parcelable