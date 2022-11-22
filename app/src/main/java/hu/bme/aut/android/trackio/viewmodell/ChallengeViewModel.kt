package hu.bme.aut.android.trackio.viewmodell

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.model.SharedPrefConfig
import hu.bme.aut.android.trackio.model.data.ChallengeDatabase
import hu.bme.aut.android.trackio.repository.ChallengeDbRepository
import hu.bme.aut.android.trackio.repository.ChallengeNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChallengeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Challenge>>
    private val challengeDbRepository: ChallengeDbRepository
    private val challengeNetworkRepository: ChallengeNetworkRepository
    init {
        val challengeDao = ChallengeDatabase.getDatabase(application).challengeDao()
        challengeDbRepository = ChallengeDbRepository(challengeDao)
        challengeNetworkRepository = ChallengeNetworkRepository()
        var token = SharedPrefConfig.getString("pref_token","no token")
        getChallengesFromServer(token)
        readAllData = challengeDbRepository.readAllData
    }

    fun addChallenge(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            challengeDbRepository.addChallenge(challenge)
        }
    }

    fun deleteChallenge(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            challengeDbRepository.deleteChallenge(challenge)
        }
    }



   /* fun onedata() {
        repository.getChallengeFromServer()?.enqueue(object : Callback<Challenge?> {

            override fun onResponse(
                call: Call<Challenge?>,
                response: Response<Challenge?>
            ) {
                Log.d("talan", "onResponse: " + response.code())
                if (response.isSuccessful) {
                    Log.d("talan", response.body().toString())
                    val data = response.body()
                    if (data != null) {
                        addChallenge(data)
                        Log.d("talan", data.id.toString())
                        Log.d("talan", data.distance.toString())
                        Log.d("talan", data.startDate.toString())
                        Log.d("talan", data.duration.toString())
                    } else {
                        Log.d("talan", "kurvaelet")
                    }
                }
            }

            override fun onFailure(
                call: Call<Challenge?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
            }
        })
    }*/





    fun getChallengesFromServer(string: String) {
        challengeNetworkRepository.getAllChallengesFromServer(string)
            ?.enqueue(object : Callback<List<Challenge?>?> {
                override fun onResponse(
                    call: Call<List<Challenge?>?>,
                    response: Response<List<Challenge?>?>
                ) {
                    Log.d("talan", "onResponse: " + response.code())
                    Log.d("talan", response.body().toString())
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            for (item in data) {
                                if (item != null) {
                                    addChallenge(item)
                                }
                            }
                        } else {
                            Log.d("talan", "lol")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Challenge?>?>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}

