package com.teachmeskills.lesson23playground.data

import retrofit2.http.GET

interface MediaService {

    @GET(URL)
    suspend fun loadVideos(): List<VideoResponseDTO>

    companion object {
        private const val URL = "https://www.dropbox.com/s/wfnm97sblyp3anj/VideoList.json?dl=1"
    }
}