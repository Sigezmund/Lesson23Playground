package com.teachmeskills.lesson23playground.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teachmeskills.lesson23playground.media.VideoEntity
import retrofit2.http.DELETE

@Dao
interface VideoDao {
    @Insert
    fun saveVideos(videos: List<VideoEntity>)

    @Query("SELECT * FROM VideoEntity")
    fun getVideos(): List<VideoEntity>

    @Delete
    fun delete(video: VideoEntity)
}