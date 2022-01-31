package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.teachmeskills.lesson23playground.media.Audio
import com.teachmeskills.lesson23playground.media.Media
import com.teachmeskills.lesson23playground.media.VideoEntity

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
        val errorView = view.findViewById<View>(R.id.errorMessage)
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        val clickListener: (Media) -> Unit = {
            (it as? VideoEntity)?.let {
                val fragment = VideoDetailsFragment.newInstance(it)
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            }
        }
        val adapter = RecyclerListAdapter(clickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
            adapter.submitList(videos) {
                recyclerView.scrollToPosition(0)
            }
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) { isRefreshing ->
            swipeRefresh.isRefreshing = isRefreshing

        }
        viewModel.isErrorMessageVisible.observe(viewLifecycleOwner) { isError ->
            errorView.isVisible = isError
        }
    }

    companion object {
        fun newInstance(value: String): RecyclerListAdapterFragment {
            return RecyclerListAdapterFragment().apply {
                arguments = bundleOf("key" to value)
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
        val item = getItem(position)

        when (holder) {
            is VideoViewHolder -> holder.video = item as VideoEntity
            is AudioViewHolder -> holder.audio = item as Audio
        }

    }

}
