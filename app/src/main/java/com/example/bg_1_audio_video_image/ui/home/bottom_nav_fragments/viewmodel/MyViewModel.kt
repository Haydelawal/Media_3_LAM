package com.example.bg_1_audio_video_image.ui.home.bottom_nav_fragments.viewmodel

import android.widget.Toast
import com.example.bg_1_audio_video_image.data.model.MyDataSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    val myVideoData = MyDataSource.VIDEO_DUMMY_DATA

    val myAudioData = MyDataSource.AUDIO_DUMMY_DATA

    /////// connectivity manager
    var networkStatus = false

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "NO INTERNET CONNECTION!!", Toast.LENGTH_SHORT).show()
        }

    }
}