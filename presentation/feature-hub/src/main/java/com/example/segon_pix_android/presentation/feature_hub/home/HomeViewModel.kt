package com.example.segon_pix_android.presentation.feature_hub.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segon_pix_android.domain.repository.ImageRepository
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
        private val imageRepository: ImageRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeState())
        val state = _state.asStateFlow()

        fun onIntent(intent: HomeIntent) {
            when (intent) {
                is HomeIntent.Init -> init(intent)
                is HomeIntent.GetMoreNewImage -> {
                    // TODO
                }
            }
        }

        private fun init(intent: HomeIntent.Init) {
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = state.value.copy(newImages = imageRepository.getRecentImages())
            }
        }
    }
