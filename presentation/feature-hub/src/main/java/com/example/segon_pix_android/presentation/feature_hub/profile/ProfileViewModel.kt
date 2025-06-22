package com.example.segon_pix_android.presentation.feature_hub.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segon_pix_android.domain.repository.AuthRepository
import com.example.segon_pix_android.domain.repository.UserRepository
import com.example.segon_pix_android.presentation.model.CoreRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state = _state.asStateFlow()

        fun onIntent(intent: ProfileIntent) {
            when (intent) {
                ProfileIntent.Init -> init()
                is ProfileIntent.SignOut -> signOut(intent)
                is ProfileIntent.DeleteAccount -> deleteAccount(intent)
                is ProfileIntent.UpdateProfile -> updateProfile(intent)
            }
        }

        private fun init() {
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = _state.value.copy(self = userRepository.getSelf())
            }
        }

        private fun signOut(intent: ProfileIntent.SignOut) {
            Log.d("ProfileViewModel", "signOut: ${state.value.self}")
            viewModelScope.launch(Dispatchers.IO) {
                authRepository.logout()
                viewModelScope.launch(Dispatchers.Main) {
                    intent.coreNavController.navigate(CoreRoute.Auth)
                }
            }
        }

        private fun deleteAccount(intent: ProfileIntent.DeleteAccount) {
            viewModelScope.launch(Dispatchers.IO) {
                userRepository.deleteUser(state.value.self.id)
                authRepository.logout()
                viewModelScope.launch(Dispatchers.Main) {
                    intent.coreNavController.navigate(CoreRoute.Auth)
                }
            }
        }

        private fun updateProfile(intent: ProfileIntent.UpdateProfile) {
        }
    }
