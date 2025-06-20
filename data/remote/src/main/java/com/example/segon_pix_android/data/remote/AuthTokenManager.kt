package com.example.segon_pix_android.data.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthTokenManager
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        private val jwtTokenKey = stringPreferencesKey("jwt_token")
        private val _currentToken = MutableStateFlow<String?>(null)
        val currentToken: StateFlow<String?> = _currentToken

        init {
            CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                // DataStoreから初期値をロード
                _currentToken.value =
                    context.dataStore.data
                        .map { preferences ->
                            preferences[jwtTokenKey]
                        }.first()
            }
        }

        fun getToken(): String? = _currentToken.value

        suspend fun saveToken(token: String) {
            context.dataStore.edit { preferences ->
                preferences[jwtTokenKey] = token
            }
            _currentToken.value = token
        }

        suspend fun clearToken() {
            context.dataStore.edit { preferences ->
                preferences.remove(jwtTokenKey)
            }
            _currentToken.value = null
        }
    }
