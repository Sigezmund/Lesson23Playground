package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.teachmeskills.lesson23playground.media.Audio
import com.teachmeskills.lesson23playground.media.Media
import com.teachmeskills.lesson23playground.media.Video

class RecyclerListAdapterFragment : Fragment() {
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
            viewModel.onRefresh()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        val clickListener: (Media) -> Unit = {
            Toast.makeText(requireContext(), "Click ${it.title}", Toast.LENGTH_SHORT).show()
        }
        val adapter = RecyclerListAdapter(clickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
            swipeRefresh.isRefreshing = false
            adapter.submitList(videos) {
                recyclerView.scrollToPosition(0)
            }
        }
    }
}

class CustomDiffUtils : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.equals(newItem)
    }

}

class RecyclerListAdapter(val clickListener: (Media) -> Unit) :
    ListAdapter<Media, RecyclerView.ViewHolder>(CustomDiffUtils()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is Video -> R.layout.video_list_item
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
        val item = getItem(position)

        when (holder) {
            is VideoViewHolder -> holder.video = item as Video
            is AudioViewHolder -> holder.audio = item as Audio
        }

    }

}
