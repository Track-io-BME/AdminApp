package hu.bme.aut.android.trackio.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AutToken(
    val token: String,
    val email :String
)
