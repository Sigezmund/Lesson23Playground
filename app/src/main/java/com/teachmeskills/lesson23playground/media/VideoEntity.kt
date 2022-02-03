package com.teachmeskills.lesson23playground.media

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class VideoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: String,
    @ColumnInfo(name = "imageUrl")
    override val imageUrl: String,
    @ColumnInfo(name = "title")
    override val title: String
) : Media, Parcelable