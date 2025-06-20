package com.example.feature_core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segon_pix_android.domain.repository.AuthRepository
import com.example.segon_pix_android.presentation.model.CoreRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CoreViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        fun onIntent(intent: CoreIntent) {
            when (intent) {
                is CoreIntent.Init -> init(intent)
            }
        }

        private fun init(intent: CoreIntent.Init) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = authRepository.isTokenVerified()
                viewModelScope.launch(Dispatchers.Main) {
                    when (result) {
                        true -> intent.coreNavHostController.navigate(CoreRoute.Hub)
                        false -> intent.coreNavHostController.navigate(CoreRoute.Auth)
                    }
                }
            }
        }
    }
