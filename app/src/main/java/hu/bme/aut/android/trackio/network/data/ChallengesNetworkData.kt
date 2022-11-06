package hu.bme.aut.android.trackio.network.data

data class ChallengesNetworkData(
    val id : Int,
    val distance : Float,
    val category : Int,
    val startDate : Long,
    val duration : Int,
    val createdAt : String,
    val updatedAt : String
)
