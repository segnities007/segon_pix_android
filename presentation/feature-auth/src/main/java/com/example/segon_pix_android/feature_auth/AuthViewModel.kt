package com.example.segon_pix_android.feature_auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segon_pix_android.domain.repository.AuthRepository
import com.example.segon_pix_android.presentation.model.CoreRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(AuthState())
        val state = _state.asStateFlow()

        fun onIntent(intent: AuthIntent) {
            when (intent) {
                is AuthIntent.GoToHub,
                -> _state.value = authReducer(state.value, intent)

                is AuthIntent.SetEmailAndPassword -> setEmailAndPassword(intent)
                is AuthIntent.GoToNextPage -> goToPage(intent)
                is AuthIntent.SignIn -> signIn(intent)
                is AuthIntent.CreateAccount -> createAccount(intent)
                is AuthIntent.BackToStart -> backToStart(intent)
            }
        }

        private fun setEmailAndPassword(intent: AuthIntent.SetEmailAndPassword) {
            _state.value =
                state.value.copy(
                    email = intent.email,
                    password = intent.password,
                )
            viewModelScope.launch(Dispatchers.IO) {
                val result = authRepository.sendEmailVerificationCode(intent.email)
                if (result) {
                    viewModelScope.launch(Dispatchers.Main) {
                        intent.pagerState.scrollToPage(3)
                    }
                }
            }
        }

        private fun backToStart(intent: AuthIntent.BackToStart) {
            _state.value = AuthState()
            viewModelScope.launch(Dispatchers.Main) {
                intent.pagerState.scrollToPage(1)
            }
        }

        private fun signIn(intent: AuthIntent.SignIn) {
            viewModelScope.launch(Dispatchers.IO) {
                val authResult = authRepository.login(intent.email, intent.password)
                if (authResult.isSuccess) {
                    viewModelScope.launch(Dispatchers.Main) {
                        intent.coreNavHostController.navigate(CoreRoute.Hub)
                    }
                }
            }
        }

        private fun goToPage(intent: AuthIntent.GoToNextPage) {
            viewModelScope.launch(Dispatchers.Main) {
                intent.pagerState.scrollToPage(intent.pageIndex)
            }
        }

        private fun createAccount(intent: AuthIntent.CreateAccount) {
            viewModelScope.launch(Dispatchers.IO) {
                val authResult =
                    authRepository.verifyAndAddUser(
                        name = intent.name,
                        email = state.value.email,
                        password = state.value.password,
                        code = intent.code,
                        birthday = intent.birthday.toInt(),
                    )
                if (authResult.isSuccess) {
                    viewModelScope.launch(Dispatchers.Main) {
                        intent.coreNavHostController.navigate(CoreRoute.Hub)
                    }
                }
            }
        }
    }
