package hu.bme.aut.android.trackio.network.data

<<<<<<< Updated upstream
data class ChallengesNetworkData(
    val id : Int,
    val distance : Float,
    val category : Int,
    val startDate : Long,
    val duration : Int,
    val createdAt : String,
    val updatedAt : String
=======
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChallengesNetworkData(
    val id : Int,
    val distance : Float,
    val sportType : Int,
    val startDate : String,
    val duration : Int
>>>>>>> Stashed changes
)
