package hu.bme.aut.android.trackio.network.api
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TrackAPI {

    @GET("admin/challanges")
    suspend fun getChallenges() : Response<List<ChallengesNetworkData>>


}