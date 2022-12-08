package com.mukesh.apicallingsample.presentation.login.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mukesh.apicallingsample.data.Response
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(
    app: Application,
    private val repository: AppRepository
) : AndroidViewModel(app) {
    private val loginResponseLiveData = MutableLiveData<Response<LoginResponse>>()

    val loginResponseData: LiveData<Response<LoginResponse>>
        get() = loginResponseLiveData

    fun login(email: String, password: String) = viewModelScope.launch {
        Log.d("login... email: ", email)
        Log.d("login... password: ", password)
        loginResponseLiveData.postValue(Response.Loading())
        val result = repository.login("eve.holt@reqres.in", "cityslicka")
        if (result != null) {
          //  loginResponseLiveData.postValue(Response.Success(result.body()))
        }

    }


}
