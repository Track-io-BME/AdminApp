package hu.bme.aut.android.trackio.network.api
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.data.AutToken
import hu.bme.aut.android.trackio.network.data.Login
import retrofit2.Call
import retrofit2.http.*

interface TrackAPI {

    @POST("admin/login")
    fun login(@Body login: Login) : Call<AutToken?>?

    @GET("admin/challenges")
    fun getChallanges(@Header("Authorization") token: String): Call<List<Challenge?>?>?

    @POST("admin/addnewchallenge")
    fun postChallenge(@Header("Authorization") token: String, @Body challenge: Challenge) : Call<Challenge?>?

    @POST("admin/deletechallenge")
    fun deleteChallenge(@Header("Authorization") token: String, @Body challenge: Challenge) : Call<Challenge?>?
}