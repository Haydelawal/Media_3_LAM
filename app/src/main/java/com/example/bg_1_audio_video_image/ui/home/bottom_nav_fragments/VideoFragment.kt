package com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bg_1_audio_video_image.data.adapter.MyVideoAdapter
import com.example.bg_1_audio_video_image.data.model.MyVideoData
import com.example.bg_1_audio_video_image.databinding.FragmentVideoBinding
import com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.viewmodel.MyViewModel
import com.example.bg_1_audio_video_image.util.NetworkListener

class VideoFragment : Fragment(), MyVideoAdapter.ClickListener {

    private lateinit var _binding: FragmentVideoBinding
    private val binding get() = _binding!!

    private val myVideoAdapter: MyVideoAdapter by lazy { MyVideoAdapter(this) }
    private val myViewModel: MyViewModel by viewModels()

    private lateinit var networkListener: NetworkListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVideoBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myVideoAdapter

        myVideoAdapter.differ.submitList(myViewModel.myVideoData)


        return binding.root
    }

    override fun onMyItemClick(myVideoData: MyVideoData) {
        lifecycleScope.launchWhenCreated {

            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext())
                .collect { status ->
                    Log.d("NetworkResult", status.toString())
                    myViewModel.networkStatus = status
//                    myViewModel.showNetworkStatus()
                }

        }


        if (myViewModel.networkStatus) {

            val action = VideoFragmentDirections.actionVideoFragmentToPlayVideoActivity(myVideoData)
            findNavController().navigate(action)
        } else {
            myViewModel.showNetworkStatus()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}