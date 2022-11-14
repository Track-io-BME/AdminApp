package hu.bme.aut.android.trackio.network.api
import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TrackAPI {

    @GET("admin/challanges")
    fun getChallenges() : Call<List<Challenge?>?>?

    @GET("admin/challange")
    fun getChallenge() : Call<Challenge?>?

}