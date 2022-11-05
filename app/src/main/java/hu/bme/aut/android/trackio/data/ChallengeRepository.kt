package hu.bme.aut.android.trackio.data

import androidx.lifecycle.LiveData

class ChallengeRepository(private val challengeDao : ChallengeDao) {

    val readAllData : LiveData<List<Challenge>> = challengeDao.readAllDate()

    suspend fun addChallenge(challenge: Challenge){
        challengeDao.addChallenge(challenge)
    }
}