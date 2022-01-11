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
import com.teachmeskills.lesson23playground.video.Video

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
            viewModel.onRefresh()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)

        val clickListener: (Int) -> Unit = { position: Int ->
            Toast.makeText(requireContext(), "Click $position", Toast.LENGTH_SHORT).show()
        }
        val adapter = RecyclerAdapter(clickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
            swipeRefresh.isRefreshing = false
            adapter.videos = videos
        }
    }
}

class RecyclerAdapter(val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    var videos: List<Video> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_list_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = videos[position]
        holder.textView.text = item.title
        holder.itemView.setOnClickListener {
            clickListener.invoke(position)
        }

        Glide.with(holder.imageView)
            .load(item.imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount() = videos.size

}


class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView = view.findViewById<TextView>(R.id.title)
    val imageView = view.findViewById<ImageView>(R.id.image)
}