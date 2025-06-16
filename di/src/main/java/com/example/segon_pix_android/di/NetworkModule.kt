package com.example.segon_pix_android.di

import com.example.segon_pix_android.data.remote.AuthTokenManager
import com.example.segon_pix_android.data.remote.service.AuthApiService
import com.example.segon_pix_android.data.remote.service.ImageApiService
import com.example.segon_pix_android.data.remote.service.UserApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            prettyPrint = true
        }

    // 認証トークンをHTTPヘッダーに付与するインターセプターの提供
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenManager: AuthTokenManager): okhttp3.Interceptor =
        okhttp3.Interceptor { chain ->
            val originalRequest = chain.request()
            val token = tokenManager.getToken()

            val requestBuilder = originalRequest.newBuilder()
            if (token != null) {
                requestBuilder.header("Authorization", "Bearer $token")
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        }

    // OkHttpClient の提供 (ロギングや認証インターセプターを追加)
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: okhttp3.Interceptor): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // リクエストとレスポンスのボディをログに出力
            }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor) // 開発時のみ有効にすることを推奨
            .addInterceptor(authInterceptor) // 認証トークン付与用
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit インスタンスの提供
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit
            .Builder()
            .baseUrl("http://localhost:8080/") // ★ あなたのバックエンドのURLに合わせて変更
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideImageApiService(retrofit: Retrofit): ImageApiService = retrofit.create(ImageApiService::class.java)
}
