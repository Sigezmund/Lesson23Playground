package com.teachmeskills.lesson23playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.teachmeskills.lesson23playground.media.VideoEntity

class VideoDetailsFragment : Fragment() {

    private var video: VideoEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            video = it.getParcelable<VideoEntity>(VIDEO_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = video?.imageUrl
    }

    companion object {

        private const val VIDEO_KEY = "video_key"

        @JvmStatic
        fun newInstance(video: VideoEntity): VideoDetailsFragment {
            val fragment = VideoDetailsFragment()
            val bundle = Bundle()
//            bundle.putSerializable(VIDEO_KEY, video)
            bundle.putParcelable(VIDEO_KEY, video)
            fragment.arguments = bundle
            return fragment
        }
//            VideoDetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable(VIDEO_KEY, video)
//                }
//            }
    }
}