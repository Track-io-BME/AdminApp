package hu.bme.aut.android.trackio.network.api
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.data.AutToken
import hu.bme.aut.android.trackio.network.data.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TrackAPI {

    @GET("admin/challanges")
    fun getChallenges(@Header("Authorization") token: String): Call<List<Challenge?>?>?

    @GET("admin/challange")
    fun getChallenge(@Header("Authorization")  token: String) : Call<Challenge?>?


    @POST("login")
    fun login(@Body login: Login) : Call<AutToken?>?

}