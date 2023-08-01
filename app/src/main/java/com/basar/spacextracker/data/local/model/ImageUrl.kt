package com.basar.spacextracker.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ImageUrl(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "url_id") val id: Int,
    @ColumnInfo(name = "url") val url: String?,
): Parcelable