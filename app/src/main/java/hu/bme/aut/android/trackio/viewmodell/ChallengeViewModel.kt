package hu.bme.aut.android.trackio.viewmodell

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.bme.aut.android.trackio.model.data.ChallengeDatabase
import hu.bme.aut.android.trackio.repository.ChallengeRepository
import hu.bme.aut.android.trackio.model.Challenge
import hu.bme.aut.android.trackio.network.data.ChallengesNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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


    fun data() = viewModelScope.launch {
        repository.getDataFromServer()
    }


}

