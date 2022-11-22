package hu.bme.aut.android.trackio.repository

import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.api.RetrofitInstance
import hu.bme.aut.android.trackio.network.data.AutToken
import hu.bme.aut.android.trackio.network.data.Login
import retrofit2.Call

class ChallengeNetworkRepository {

    fun getAllChallengesFromServer(autToken: String): Call<List<Challenge?>?>?  {
        return RetrofitInstance.api.getChallenges("Bearer $autToken")
    }

    fun getChallengeFromServer(autToken: String) : Call<Challenge?>? {
        return RetrofitInstance.api.getChallenge("Bearer $autToken")
    }

    fun loginToServer(login: Login) :  Call<AutToken?>? {
        return RetrofitInstance.api.login(login)
    }

}