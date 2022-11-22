package hu.bme.aut.android.trackio.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    val email: String,
    val password: String
)
