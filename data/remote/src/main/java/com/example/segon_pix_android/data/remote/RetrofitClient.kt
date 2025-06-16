package com.example.segon_pix_android.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder

object RetrofitClient {
    private val networkJson = Json { ignoreUnknownKeys = true }
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofit: Retrofit =
        Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
}
