package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.teachmeskills.lesson23playground.media.VideoEntity

class ListViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val listView = view.findViewById<ListView>(R.id.listView)

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(requireContext(), "Click $i", Toast.LENGTH_SHORT).show()
        }

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        val adapter = VideosAdapter()
        listView.adapter = adapter
        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
//            adapter.videos = videos
            swipeRefresh.isRefreshing = false

        }
    }
}

class VideosAdapter() : BaseAdapter() {

    var videos: List<VideoEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = videos.size

    override fun getItem(position: Int): Any = videos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(index: Int, child: View?, parent: ViewGroup): View {

        val viewHolder = if (child == null) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.video_list_item, parent, false)

            val viewHolder = ViewHolder(view)
            view.tag = viewHolder
            viewHolder
        } else {
            child.tag as ViewHolder
        }


        val video = videos[index]

        viewHolder.textView.text = video.title

        return viewHolder.view
    }

    class ViewHolder(val view: View) {
        val textView = view.findViewById<TextView>(R.id.title)
        val imageView = view.findViewById<ImageView>(R.id.image)
    }
}