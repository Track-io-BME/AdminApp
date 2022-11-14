package hu.bme.aut.android.trackio.viewmodell

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
<<<<<<< Updated upstream
=======
import hu.bme.aut.android.trackio.model.Challenge
>>>>>>> Stashed changes
import hu.bme.aut.android.trackio.model.data.ChallengeDatabase
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import hu.bme.aut.android.trackio.repository.ChallengeRepository
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
<<<<<<< Updated upstream
import retrofit2.Response
=======
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

>>>>>>> Stashed changes

class ChallengeViewModel(application: Application) :AndroidViewModel(application) {

    val readAllData : LiveData<List<Challenge>>
    private val repository : ChallengeRepository
    val responseData : MutableLiveData<Response<List<ChallengesNetworkData>>> = MutableLiveData()
    lateinit var listresponse : List<ChallengesNetworkData>

    init{
        val challengeDao = ChallengeDatabase.getDatabase(application).challengeDao()
        repository = ChallengeRepository(challengeDao)
        readAllData = repository.readAllData
    }

    fun addChallenge(challenge: Challenge){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChallenge(challenge)
        }
    }

    fun deleteChallenge(challenge: Challenge){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteChallenge(challenge)
        }
    }

<<<<<<< Updated upstream

    fun data() = viewModelScope.launch {
        repository.getDataFromServer()
=======
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
            ?.enqueue(object : Callback<List<ChallengesNetworkData?>?> {
                override fun onResponse(
                    call: Call<List<ChallengesNetworkData?>?>,
                    response: Response<List<ChallengesNetworkData?>?>
                ) {
                    Log.d("talan", "onResponse: " + response.code())
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            for (item in data) {
                                if (item != null) {
                                    addChallenge(
                                        Challenge(
                                            item.id,
                                            item.distance,
                                            Challenge.SportType.WALKING,
                                            12121,
                                            item.sportType
                                        )
                                    )
                                }
                            }
                        } else {
                            Log.d("talan", "kurvaelet")
                        }
                    }
                }

                override fun onFailure(call: Call<List<ChallengesNetworkData?>?>, t: Throwable) {
                    t.printStackTrace()
                }
            })
>>>>>>> Stashed changes
    }


}

