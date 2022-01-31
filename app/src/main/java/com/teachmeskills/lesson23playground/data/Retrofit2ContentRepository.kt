package com.teachmeskills.lesson23playground.data

import com.teachmeskills.lesson23playground.media.Media
import com.teachmeskills.lesson23playground.media.VideoEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit2ContentRepository : ContentRepository {
    private val mediaService: MediaService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.dropbox.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mediaService = retrofit.create(MediaService::class.java)

    }

    override suspend fun getMedia(): List<Media> {

        return mediaService.loadMedia("wfnm97sblyp3anj").videos?.map {
            VideoEntity(
                id = it.uniqueId.orEmpty(),
                title = it.title.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        }.orEmpty()
    }
}