package hu.bme.aut.android.trackio.repository

import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.model.data.ChallengeDao
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.api.RetrofitInstance
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import retrofit2.Response


class ChallengeRepository(private val challengeDao : ChallengeDao) {

    val readAllData : LiveData<List<Challenge>> = challengeDao.readAllDate()

    suspend fun addChallenge(challenge: Challenge){
        challengeDao.addChallenge(challenge)
    }

    suspend fun deleteChallenge(challenge: Challenge){
        challengeDao.deleteChallenge(challenge)
    }

    suspend fun getChallenges() : Response<List<ChallengesNetworkData>> {
        return RetrofitInstance.api.getChallenges()
    }
}