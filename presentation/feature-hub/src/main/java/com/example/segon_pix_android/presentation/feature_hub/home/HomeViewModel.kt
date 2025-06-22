package com.example.segon_pix_android.presentation.feature_hub.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segon_pix_android.domain.repository.ImageRepository
import com.example.segon_pix_android.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val repository: UserRepository,
        private val imageRepository: ImageRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state = _state.asStateFlow()

        fun onIntent(intent: HomeIntent) {
            when (intent) {
                is HomeIntent.Init -> init()
                is HomeIntent.GetMoreNewImage -> getMoreNewImage(intent)
                is HomeIntent.PostImage -> postImage(intent)
                HomeIntent.GetNewImage -> getNewImage()
            }
        }

        private fun init() {
            getNewImage()
        }

        private fun getNewImage() {
            viewModelScope.launch(Dispatchers.IO) {
                val newImage = imageRepository.getImages("")
                if (newImage.isEmpty()) {
                    _state.value = state.value.copy(isFetchCompleted = true)
                    return@launch
                }
                _state.value = state.value.copy(newImages = newImage)
            }
        }

        private fun getMoreNewImage(intent: HomeIntent.GetMoreNewImage) {
            viewModelScope.launch(Dispatchers.IO) {
                val newImage = imageRepository.getImages(hashtag = "", imageId = intent.last)
                if (newImage.isEmpty()) {
                    _state.value = state.value.copy(isFetchCompleted = true)
                    return@launch
                }
                _state.value = state.value.copy(newImages = state.value.newImages + newImage)
            }
        }

        private fun postImage(intent: HomeIntent.PostImage) {
            viewModelScope.launch(Dispatchers.IO) {
                val self = repository.getSelf()
                val hashtags = intent.hashtag.split(" ")
                val result = imageRepository.addImage(self.id, intent.uri, hashtags)
                if (result) intent.onDismiss()
            }
        }
    }
