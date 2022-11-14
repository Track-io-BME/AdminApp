package hu.bme.aut.android.trackio.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChallengesNetworkData(
    val id : Int,
    val distance : Float,
    val sportType : Int,
    val startDate : String,
    val duration : Int
)
