package hu.bme.aut.android.trackio.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChallengeViewModel(application: Application) :AndroidViewModel(application) {

    val readAllData : LiveData<List<Challenge>>
    private val repository : ChallengeRepository

    init{
        val challengeDao = ChallengeDatabase.getDatabase(application).challengeDao()
        repository = ChallengeRepository(challengeDao)
        readAllData = repository.readAllData
    }

    fun addUser(challenge: Challenge){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChallenge(challenge)
        }
    }
}