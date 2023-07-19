package com.example.bg_1_audio_video_image.datastore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bg_1_audio_video_image.datastore.model.AudioVideoImageDataStore
import com.example.bg_1_audio_video_image.datastore.repo.AudioVideoImageDataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private  val audioVideoImageDataStoreRepo: AudioVideoImageDataStoreRepo): ViewModel() {

    var finished: MutableLiveData<Boolean> = MutableLiveData()

    var audioVideoImageDataStoreValue: MutableLiveData<AudioVideoImageDataStore> = MutableLiveData()

    ///// me
    fun showTheData(): LiveData<AudioVideoImageDataStore> {
        return audioVideoImageDataStoreValue
    }

    /////
    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            audioVideoImageDataStoreRepo.saveDataStore(
                AudioVideoImageDataStore(
                    finished = finished.value!!
                )
            )
        }
    }

    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            audioVideoImageDataStoreRepo.getDataStore().collect {
                audioVideoImageDataStoreValue.postValue(it)
            }
        }
    }
}