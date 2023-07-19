package com.example.bg_1_audio_video_image.datastore.model

import kotlinx.coroutines.flow.Flow

interface AudioVideoImageDataStoreInterface {

    suspend fun saveDataStore(AudioVideoImageDataStore: AudioVideoImageDataStore)

    suspend fun getDataStore(): Flow<AudioVideoImageDataStore>

}