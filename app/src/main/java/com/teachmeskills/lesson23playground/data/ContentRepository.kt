package com.teachmeskills.lesson23playground.data

import com.teachmeskills.lesson23playground.media.Media

interface ContentRepository {
    suspend fun getMedia(): List<Media>
}