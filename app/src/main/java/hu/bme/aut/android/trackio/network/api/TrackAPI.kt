package hu.bme.aut.android.trackio.network.api
import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TrackAPI {

    @GET("admin/challanges")
<<<<<<< Updated upstream
    suspend fun getChallenges() : List<ChallengesNetworkData>
=======
    fun getChallenges() : Call<List<ChallengesNetworkData?>?>?

    @GET("admin/challange")
    fun getChallenge() : Call<Challenge?>?
>>>>>>> Stashed changes


}