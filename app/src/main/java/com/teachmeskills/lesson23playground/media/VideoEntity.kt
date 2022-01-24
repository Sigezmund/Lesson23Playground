package com.teachmeskills.lesson23playground.media

data class VideoEntity(
    override val id: String,
    override val imageUrl: String,
    override val title: String
): Media