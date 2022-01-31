package com.teachmeskills.lesson23playground.media

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoEntity(
    override val id: String,
    override val imageUrl: String,
    override val title: String
) : Media, Parcelable