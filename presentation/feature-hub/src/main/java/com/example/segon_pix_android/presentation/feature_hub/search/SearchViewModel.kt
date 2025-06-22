package com.example.segon_pix_android.presentation.feature_hub.search

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
class SearchViewModel
    @Inject
    constructor(
        private val imageRepository: ImageRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(SearchState())
        val state = _state.asStateFlow()

        fun onIntent(intent: SearchIntent) {
            when (intent) {
                is SearchIntent.Search -> search(intent)
                is SearchIntent.Init -> init()
                SearchIntent.GetMoreImages -> getMoreImages()
                is SearchIntent.SetQuery -> setQuery(intent)
            }
        }

        private fun init() {
            // nothing
        }

        private fun setQuery(intent: SearchIntent.SetQuery) {
            _state.value = _state.value.copy(query = intent.query)
        }

        private fun search(intent: SearchIntent.Search) {
            viewModelScope.launch(Dispatchers.IO) {
                val images = imageRepository.getImages(intent.query)
                if (images.isEmpty()) {
                    _state.value = _state.value.copy(isFetchCompleted = true)
                    return@launch
                }
                _state.value =
                    _state.value.copy(
                        images = _state.value.images + images,
                    )
            }
        }

        private fun getMoreImages() {
            viewModelScope.launch(Dispatchers.IO) {
                val images =
                    imageRepository.getImages(
                        hashtag = _state.value.query,
                        imageId =
                            _state.value.images
                                .last()
                                .id,
                    )
                if (images.isEmpty()) {
                    _state.value = _state.value.copy(isFetchCompleted = true)
                    return@launch
                }
                _state.value =
                    _state.value.copy(
                        images = _state.value.images + images,
                    )
            }
        }
    }
