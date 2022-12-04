package hu.bme.aut.android.trackio.repository

import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.model.data.ChallengeDao
import hu.bme.aut.android.trackio.model.Challenge

class ChallengeDbRepository(private val challengeDao : ChallengeDao) {

    val readAllData : LiveData<List<Challenge>> = challengeDao.readAllDate()

    suspend fun addChallenge(challenge: Challenge){
        challengeDao.addChallenge(challenge)
    }

    suspend fun deleteChallenge(challenge: Challenge){
        challengeDao.deleteChallenge(challenge)
    }

    fun deleteAllChallenge(){
        challengeDao.deleteAllChallenges()
    }



}