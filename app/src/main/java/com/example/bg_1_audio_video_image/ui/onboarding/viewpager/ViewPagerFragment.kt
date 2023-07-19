package com.example.bg_1_audio_video_image.ui.onboarding.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.databinding.FragmentViewPagerBinding
import com.example.bg_1_audio_video_image.ui.onboarding.viewpager.screens.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private lateinit var _binding: FragmentViewPagerBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment(),
            FourthScreenFragment(),
            FifthScreenFragment()
        )

        val adapter = ViewPagerAdapter(
            list = fragmentList,
            fm = childFragmentManager,
            lifecycle = lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}