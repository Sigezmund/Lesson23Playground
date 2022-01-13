package com.teachmeskills.lesson23playground.media

data class Video(
    override val id: String,
    override val imageUrl: String,
    override val title: String
): Media