package com.example.bg_1_audio_video_image.datastore.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.bg_1_audio_video_image.datastore.model.AudioVideoImageDataStore
import com.example.bg_1_audio_video_image.datastore.model.AudioVideoImageDataStoreInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DataStore_NAME = "AudioVideoImage_Datastore"
val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)


class AudioVideoImageDataStoreRepo(private val context: Context) : AudioVideoImageDataStoreInterface {

    companion object {
        val FINISHED = booleanPreferencesKey("FINISHED")

    }

    override suspend fun saveDataStore(AudioVideoImageDataStore: AudioVideoImageDataStore) {
        context.datastore.edit { DS2s ->
            DS2s[FINISHED] = AudioVideoImageDataStore.finished

        }
    }

    override suspend fun getDataStore(): Flow<AudioVideoImageDataStore> =
        context.datastore.data.map { ds2 ->
            AudioVideoImageDataStore(
                finished = ds2[FINISHED] ?: false
            )

        }

}

