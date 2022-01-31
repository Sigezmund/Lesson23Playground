package com.teachmeskills.lesson23playground.data

import retrofit2.http.GET
import retrofit2.http.Path


interface MediaService {

    @GET("s/{key}/VideoList.json?dl=1")
    suspend fun loadMedia(@Path("key") param: String): VideoResponseDTO

}