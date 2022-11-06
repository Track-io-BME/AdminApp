package hu.bme.aut.android.trackio.network

import hu.bme.aut.android.trackio.model.Challenge
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    private val retrofit: Retrofit
    private val trackAPI: TrackAPI

    private val SERVICE_URL = "https://23.97.188.188:443"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        trackAPI = retrofit.create(TrackAPI::class.java)
    }

    fun getChallenge() : Call<Challenge?>?{
        //return trackAPI.getChallenge()
        return null
    }
}