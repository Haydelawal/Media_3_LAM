package com.example.bg_1_audio_video_image.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MyVideoData(

    val id: Int,
    val title: String,
    val thumbnail: String,
    val media_url: String,
    val publisher: String
) : Parcelable