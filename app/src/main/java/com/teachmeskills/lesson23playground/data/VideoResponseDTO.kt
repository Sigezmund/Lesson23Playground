package com.teachmeskills.lesson23playground.data

import com.google.gson.annotations.SerializedName

data class VideoResponseDTO(
    @SerializedName("videos")
    val videos: List<VideoDTO>? = null
)

data class VideoDTO(
    @SerializedName("id")
    val uniqueId: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("title")
    val title: String? = null
)