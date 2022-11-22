package hu.bme.aut.android.trackio.repository

import android.util.Log
import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.model.data.ChallengeDao
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.api.RetrofitInstance
import hu.bme.aut.android.trackio.network.data.AutToken
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import hu.bme.aut.android.trackio.network.data.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body


class ChallengeDbRepository(private val challengeDao : ChallengeDao) {

    val readAllData : LiveData<List<Challenge>> = challengeDao.readAllDate()

    suspend fun addChallenge(challenge: Challenge){
        challengeDao.addChallenge(challenge)
    }

    suspend fun deleteChallenge(challenge: Challenge){
        challengeDao.deleteChallenge(challenge)
    }



}