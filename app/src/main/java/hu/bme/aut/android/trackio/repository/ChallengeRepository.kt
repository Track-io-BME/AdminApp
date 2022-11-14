package hu.bme.aut.android.trackio.repository

import android.util.Log
import androidx.lifecycle.LiveData
import hu.bme.aut.android.trackio.model.data.ChallengeDao
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.api.RetrofitInstance
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class ChallengeRepository(private val challengeDao : ChallengeDao) {

    val readAllData : LiveData<List<Challenge>> = challengeDao.readAllDate()

    suspend fun addChallenge(challenge: Challenge){
        challengeDao.addChallenge(challenge)
    }

    suspend fun deleteChallenge(challenge: Challenge){
        challengeDao.deleteChallenge(challenge)
    }

<<<<<<< Updated upstream
    suspend fun getChallenges() : List<ChallengesNetworkData> {
        return RetrofitInstance.api.getChallenges()
    }

    suspend fun getDataFromServer() = withContext(Dispatchers.IO){
        val data = getChallenges()
        Log.d("talan",data.toString())
        /*for(item in data.toList()){
            addChallenge( Challenge(item.id,item.distance,
                Challenge.SportType.getByOrdinal(item.category)!!,item.duration,item.startDate)
            )
        }*/
=======
    suspend fun deleteAllChallenges(){
        challengeDao.deleteAllChallenges()
    }

    suspend fun createChallenge(challenge: Challenge)
    {
        addChallenge(challenge)
    }

    fun getAllChallengesFromServer(): Call<List<ChallengesNetworkData?>?>?  {
        return RetrofitInstance.api.getChallenges()
    }

    fun getChallengeFromServer() : Call<Challenge?>? {
        return RetrofitInstance.api.getChallenge()
>>>>>>> Stashed changes
    }


}