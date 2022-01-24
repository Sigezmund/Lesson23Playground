package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.teachmeskills.lesson23playground.media.Audio
import com.teachmeskills.lesson23playground.media.Media
import com.teachmeskills.lesson23playground.media.VideoEntity

class RecyclerViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        val clickListener: (Media) -> Unit = {
            Toast.makeText(requireContext(), "Click ${it.title}", Toast.LENGTH_SHORT).show()
        }
        val adapter = RecyclerAdapter(clickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
            swipeRefresh.isRefreshing = false
            adapter.media = videos
        }
    }
}

class RecyclerAdapter(val clickListener: (Media) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var media: List<Media> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        val item = media[position]
        return when (item) {
            is VideoEntity -> R.layout.video_list_item
            is Audio -> R.layout.audio_list_item
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.video_list_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_list_item, parent, false)
                VideoViewHolder(view, clickListener)
            }
            R.layout.audio_list_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.audio_list_item, parent, false)
                AudioViewHolder(view, clickListener)
            }
            else -> throw IllegalArgumentException("Unknown type $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = media[position]

        when (holder) {
            is VideoViewHolder -> holder.video = item as VideoEntity
            is AudioViewHolder -> holder.audio = item as Audio
        }

    }

    override fun getItemCount() = media.size

}


class VideoViewHolder(view: View, click: (Media) -> Unit) : RecyclerView.ViewHolder(view) {
    val textView = view.findViewById<TextView>(R.id.title)
    val imageView = view.findViewById<ImageView>(R.id.image)


    init {
        view.setOnClickListener {
            video?.let {
                click(it)
            }


        }
    }


    var video: VideoEntity? = null
        set(value) {
            field = value
            textView.text = value?.title

            Glide.with(imageView)
                .load(value?.imageUrl)
                .into(imageView)
        }
}

class AudioViewHolder(view: View, click: (Media) -> Unit) : RecyclerView.ViewHolder(view) {
    val textView = view.findViewById<TextView>(R.id.title)
    val imageView = view.findViewById<ImageView>(R.id.image)


    init {
        view.setOnClickListener {
            audio?.let {
                click(it)
            }
        }
    }

    var audio: Audio? = null
        set(value) {
            field = value
            textView.text = value?.title

            Glide.with(imageView)
                .load(value?.imageUrl)
                .into(imageView)
        }
}