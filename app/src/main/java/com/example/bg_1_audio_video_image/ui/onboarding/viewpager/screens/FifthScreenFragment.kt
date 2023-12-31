package com.example.bg_1_audio_video_image.ui.onboarding.viewpager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.databinding.FragmentFifthScreenBinding
import com.example.bg_1_audio_video_image.databinding.FragmentFourthScreenBinding
import com.example.bg_1_audio_video_image.datastore.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthScreenFragment : Fragment() {

    private lateinit var _binding: FragmentFifthScreenBinding
    private val binding get() = _binding!!
    private val myViewModel: DataStoreViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFifthScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {

            val viewPagerFinished = true

            myViewModel.finished.value = viewPagerFinished
            myViewModel.saveData()

            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
            activity?.finish()

        }


        binding.previous.setOnClickListener {
            // array, so second fragment = 1
            viewPager?.currentItem = 3
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}