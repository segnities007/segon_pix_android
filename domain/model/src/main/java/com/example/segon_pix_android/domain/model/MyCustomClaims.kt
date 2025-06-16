package com.example.segon_pix_android.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyCustomClaims(
    @SerialName("email")
    val email: String,
    @SerialName("user_id")
    val userId: Long, // Goのuintに合わせる
    // jwt.RegisteredClaims に相当するフィールド。
    // JWTライブラリによっては、これらのフィールドを直接データクラスに含める必要がある場合と、
    // ベースクラス/インターフェースとして提供される場合があります。
    // 一般的なJWTライブラリ (例: auth0/java-jwt) を使う場合、RegisteredClaimsはライブラリ側で処理されます。
    // ここでは、よく使われるRegisteredClaimsのフィールドを直接追加します。
    @SerialName("iss") // Issuer
    val issuer: String? = null,
    @SerialName("sub") // Subject
    val subject: String? = null,
    @SerialName("aud") // Audience
    val audience: List<String>? = null,
    @SerialName("exp") // Expiration Time
    val expiration: Long? = null, // Unixタイムスタンプ (秒)
    @SerialName("nbf") // Not Before
    val notBefore: Long? = null,
    @SerialName("iat") // Issued At
    val issuedAt: Long? = null,
    @SerialName("jti") // JWT ID
    val jwtId: String? = null,
)
