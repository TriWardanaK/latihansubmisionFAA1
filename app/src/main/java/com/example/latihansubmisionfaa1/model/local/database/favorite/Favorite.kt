package com.example.latihansubmisionfaa1.model.local.database.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user")
    var user: String = "",

    @ColumnInfo(name = "avatar")
    var avatar: String? = null
) : Parcelable