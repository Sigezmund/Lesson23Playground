package com.teachmeskills.lesson23playground.data

import com.google.gson.Gson
import com.teachmeskills.lesson23playground.media.Media
import com.teachmeskills.lesson23playground.media.VideoEntity
import okhttp3.OkHttpClient
import okhttp3.Request

class OkhttpContentRepository : ContentRepository {

    private val gson = Gson()

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    override suspend fun getMedia(): List<Media> {
        val response = okHttpClient
            .newCall(
                Request.Builder()
                    .url(URL)
                    .build()
            ).execute()

        val jsonString = response.body?.string().orEmpty()

        val json = gson.fromJson(jsonString, VideoResponseDTO::class.java)


        return json.videos?.map {
            VideoEntity(
                id = it.uniqueId.orEmpty(),
                title = it.title.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        }.orEmpty()
    }

    companion object {
        private const val URL = "https://www.dropbox.com/s/wfnm97sblyp3anj/VideoList.json?dl=1"
    }


}