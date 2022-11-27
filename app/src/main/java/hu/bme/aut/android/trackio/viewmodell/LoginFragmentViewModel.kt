package hu.bme.aut.android.trackio.viewmodell

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.trackio.model.SharedPrefConfig
import hu.bme.aut.android.trackio.network.data.AutToken
import hu.bme.aut.android.trackio.network.data.Login
import hu.bme.aut.android.trackio.repository.ChallengeNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class LoginFragmentViewModel: ViewModel() {

    private val networkRepository: ChallengeNetworkRepository = ChallengeNetworkRepository()

    fun login(login: Login) : LiveData<Boolean> {
        var autToken : AutToken
        var succefulLogin = MutableLiveData<Boolean>()
        networkRepository.loginToServer(login)?.enqueue(object : Callback<AutToken?> {
            override fun onResponse(call: Call<AutToken?>, response: Response<AutToken?>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        autToken = response.body()!!
                        Log.d("talan",response.body().toString())
                        SharedPrefConfig.put("pref_token","Bearer "+autToken.token)
                        SharedPrefConfig.put("pref_password",login.password)
                        SharedPrefConfig.put("pref_email",autToken.email)
                        SharedPrefConfig.put("pref_loggedin",true)
                        val expiry_date = Calendar.getInstance().getTime().time+3600000
                        SharedPrefConfig.put("pref_expiry_date",expiry_date)
                        succefulLogin.value=true
                    }

                }
                else{
                    succefulLogin.value=false
                }
            }

            override fun onFailure(call: Call<AutToken?>, t: Throwable) {

                t.printStackTrace()
            }

        })
        return succefulLogin
    }



}