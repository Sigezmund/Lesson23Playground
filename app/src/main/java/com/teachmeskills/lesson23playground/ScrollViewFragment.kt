package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ScrollViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scroll_view_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val linear = view.findViewById<LinearLayout>(R.id.linearLayout)
        viewModel.videosLiveData.observe(viewLifecycleOwner) { videos ->
            linear.removeAllViews()

            videos.forEach { video ->
                val view = LayoutInflater.from(requireContext())
                    .inflate(R.layout.video_list_item, linear, false)

                view.findViewById<TextView>(R.id.title).text = video.title

                linear.addView(view)
            }
        }
    }
}