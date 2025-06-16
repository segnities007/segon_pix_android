package com.example.segon_pix_android.data.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthTokenManager
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        private val jwtTokenKey = stringPreferencesKey("jwt_token")

        suspend fun getToken(): String? =
            context.dataStore.data
                .map { preferences ->
                    preferences[jwtTokenKey]
                }.first()

        suspend fun saveToken(token: String) {
            context.dataStore.edit { preferences ->
                preferences[jwtTokenKey] = token
            }
        }

        suspend fun clearToken() {
            context.dataStore.edit { preferences ->
                preferences.remove(jwtTokenKey)
            }
        }
    }
