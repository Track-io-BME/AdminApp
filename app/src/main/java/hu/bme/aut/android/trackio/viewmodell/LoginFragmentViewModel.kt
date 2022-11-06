package hu.bme.aut.android.trackio.viewmodell

import androidx.lifecycle.ViewModel


class LoginFragmentViewModel: ViewModel() {

    fun loginUser(email : String,  password : String) : Boolean{
        return  true
        if(email == "email" && password == "password"){
            return  true
        }
        return false
    }

}