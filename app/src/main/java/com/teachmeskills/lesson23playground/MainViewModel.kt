package com.teachmeskills.lesson23playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teachmeskills.lesson23playground.video.Video
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main)
    val videosLiveData = MutableLiveData<List<Video>>()

    fun onRefresh() {
        scope.launch {
            delay(2000)

            val videosList = mutableListOf<Video>()
            videosList.add(
                Video(
                    "asdas",
                    "test Url",
                    title = "Test title"
                )
            )
            videosList.addAll(videosLiveData.value.orEmpty())
            videosLiveData.value = videosList
        }

    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


    init {
        videosLiveData.value = listOf(
            Video(
                id = "RgO_J0eRFsg",
                imageUrl = "https://i.ytimg.com/vi/RgO_J0eRFsg/hqdefault.jpg",
                title = "9 gennaio 1900 | Da 122 anni siamo la storia di Roma"
            ),
            Video(
                id = "aNMxtguT0yY",
                imageUrl = "https://i.ytimg.com/vi/aNMxtguT0yY/maxresdefault.jpg",
                title = "Serie A TIM | Lazio-Empoli 3-3 - Highlights"
            ),
            Video(
                id = "9mIGPYqNOSE",
                imageUrl = "https://i.ytimg.com/vi/9mIGPYqNOSE/hqdefault.jpg",
                title = "Serie A TIM | Venezia-Lazio 1-3 - Highlights"
            ),
            Video(
                id = "FZ-Eh5A8CfE",
                imageUrl = "https://i.ytimg.com/vi/FZ-Eh5A8CfE/maxresdefault.jpg",
                title = "Serie A TIM | Lazio-Genoa 3-1 - Highlights"
            ),
            Video(
                id = "iWQAIv-gRBg",
                imageUrl = "https://i.ytimg.com/vi/iWQAIv-gRBg/maxresdefault.jpg",
                title = "#LazioGenoa | Conferenza stampa mister Sarri"
            ),
            Video(
                id = "p6cduxFKcc0",
                imageUrl = "https://i.ytimg.com/vi/p6cduxFKcc0/maxresdefault.jpg",
                title = "Primavera 2 | Lazio-Pisa 3-0 - Highlights"
            ),
            Video(
                id = "WVaVIWfFMOw",
                imageUrl = "https://i.ytimg.com/vi/WVaVIWfFMOw/maxresdefault.jpg",
                title = "Lazio-Galatasaray | Conferenza stampa Maurizio Sarri ed Elseid Hysaj"
            ),
            Video(
                id = "03UDoDVejtU",
                imageUrl = "https://i.ytimg.com/vi/03UDoDVejtU/maxresdefault.jpg",
                title = "Serie A TIM | Sampdoria-Lazio 1-3 - Highlights"
            ),
            Video(
                id = "jh2usgm76Ao",
                imageUrl = "https://i.ytimg.com/vi/jh2usgm76Ao/hqdefault.jpg",
                title = "Primavera 2 | Cosenza-Lazio 1-2 - Highlights"
            ),
            Video(
                id = "ZYAseVCkchs",
                imageUrl = "https://i.ytimg.com/vi/ZYAseVCkchs/hqdefault.jpg",
                title = "Serie A TIM | Lazio-Udinese 4-4 - Highlights"
            ),
            Video(
                id = "3oxvogWQCgo",
                imageUrl = "https://i.ytimg.com/vi/3oxvogWQCgo/maxresdefault.jpg",
                title = "#LazioUdinese | Conferenza stampa mister Sarri"
            ),
            Video(
                id = "-7TnHYNJuyE",
                imageUrl = "https://i.ytimg.com/vi/-7TnHYNJuyE/hqdefault.jpg",
                title = "Pepe Reina e Lorenzo Capone ci parlano dei LAZIO Fan Token"
            ),
            Video(
                id = "v1blSopPw34",
                imageUrl = "https://i.ytimg.com/vi/v1blSopPw34/hqdefault.jpg",
                title = "Serie A TIM | Lazio-Salernitana 3-0 - Highlights"
            ),
            Video(
                id = "MOROL1vKWsY",
                imageUrl = "https://i.ytimg.com/vi/MOROL1vKWsY/maxresdefault.jpg",
                title = "Pepe Reina in conferenza stampa:  Lazio -Salernitana è una finale per noi "
            ),
            Video(
                id = "iFX95d2qFpQ",
                imageUrl = "https://i.ytimg.com/vi/iFX95d2qFpQ/maxresdefault.jpg",
                title = "Ciro Immobile | All 159 S.S. Lazio goals"
            ),
            Video(
                id = "b85E_8rroBo",
                imageUrl = "https://i.ytimg.com/vi/b85E_8rroBo/maxresdefault.jpg",
                title = "Serie A TIM | Atalanta-Lazio 2-2 - Highlights"
            ),
            Video(
                id = "0TheIhTf1Jk",
                imageUrl = "https://i.ytimg.com/vi/0TheIhTf1Jk/maxresdefault.jpg",
                title = "Binance | A cosa servono i Lazio Fan Token"
            ),
            Video(
                id = "eDXVbbrh9s0",
                imageUrl = "https://i.ytimg.com/vi/eDXVbbrh9s0/maxresdefault.jpg",
                title = "Serie A TIM | Lazio-Fiorentina 1-0 - Highlights"
            ),
            Video(
                id = "G3NmjQqlIc4",
                imageUrl = "https://i.ytimg.com/vi/G3NmjQqlIc4/hqdefault.jpg",
                title = "Primavera 2 | Highlights Lazio-Cesena 4-2"
            ),
            Video(
                id = "S2Ivrrww8HA",
                imageUrl = "https://i.ytimg.com/vi/S2Ivrrww8HA/maxresdefault.jpg",
                title = "Binance nuovo Main Jersey Sponsor della S.S. Lazio"
            ),
            Video(
                id = "7mt186dCzr0",
                imageUrl = "https://i.ytimg.com/vi/7mt186dCzr0/maxresdefault.jpg",
                title = "Serie A TIM | Lazio-Inter 3-1 - Highlights"
            ),

            )
    }
}