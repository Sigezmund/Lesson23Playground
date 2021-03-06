package com.teachmeskills.lesson23playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teachmeskills.lesson23playground.data.ContentRepository
import com.teachmeskills.lesson23playground.media.Media
import kotlinx.coroutines.*

//https://www.dropbox.com/s/wfnm97sblyp3anj/VideoList.txt?dl=1
//https://www.dropbox.com/s/031abeols3vvyb2/AudioList.json?dl=1

class MainViewModel(
    private val contentRepository: ContentRepository
) : ViewModel() {


    private val scope = CoroutineScope(Dispatchers.Main)
    val videosLiveData = MutableLiveData<List<Media>>()
    val isRefreshing = MutableLiveData<Boolean>()
    val isErrorMessageVisible = MutableLiveData<Boolean>()

    init {
        refresh()
    }

    fun refresh() {
        scope.launch {
            isRefreshing.value = true
            isErrorMessageVisible.value = false
            try {
                val videos = withContext(Dispatchers.IO) {
                    contentRepository.getMedia()
                }
                videosLiveData.value = videos
            } catch (e: Exception) {
                if (videosLiveData.value.isNullOrEmpty()) {
                    isErrorMessageVisible.value = true
                }
            }
            isRefreshing.value = false

        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}