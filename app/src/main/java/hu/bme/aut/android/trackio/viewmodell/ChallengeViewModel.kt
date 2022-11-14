package hu.bme.aut.android.trackio.viewmodell

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.model.data.ChallengeDatabase
import hu.bme.aut.android.trackio.repository.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChallengeViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Challenge>>
    private val repository: ChallengeRepository

    init {
        val challengeDao = ChallengeDatabase.getDatabase(application).challengeDao()
        repository = ChallengeRepository(challengeDao)
        onedata()
        readAllData = repository.readAllData
    }

    fun addChallenge(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChallenge(challenge)
        }
    }

    fun deleteChallenge(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChallenge(challenge)
        }
    }


    fun onedata() {
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
    }

    fun data() {
        repository.getAllChallengesFromServer()
            ?.enqueue(object : Callback<List<Challenge?>?> {
                override fun onResponse(
                    call: Call<List<Challenge?>?>,
                    response: Response<List<Challenge?>?>
                ) {
                    Log.d("talan", "onResponse: " + response.code())
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            for (item in data) {
                                if (item != null) {
                                    addChallenge(item)
                                }
                            }
                        } else {
                            Log.d("talan", "kurvaelet")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Challenge?>?>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}

