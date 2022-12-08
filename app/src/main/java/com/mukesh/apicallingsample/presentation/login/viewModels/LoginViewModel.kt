package com.mukesh.apicallingsample.presentation.login.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mukesh.apicallingsample.R
import com.mukesh.apicallingsample.data.Response
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import com.mukesh.apicallingsample.presentation.login.model.TokenResponse
import com.sk.user.agent.optimizecode.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    app: Application,
    private val repository: AppRepository
) : AndroidViewModel(app) {
    private val loginLiveData = MutableLiveData<Response<LoginResponse>>()
    val loginData: LiveData<Response<LoginResponse>>
        get() = loginLiveData

    fun callToken(username: String, password: String?) =
        viewModelScope.launch(Dispatchers.IO) {
                loginLiveData.postValue(Response.Loading())
                val result = repository.doLogin(username, password)
                if (result?.body() != null) {
                    // getApplication<MyApplication>().login = result.body()!!.access_login.toString()
                    loginLiveData.postValue(Response.Success(result?.body()))
                } else {
                    loginLiveData.postValue(Response.Error(getApplication<Application>().getString(R.string.msg_improper_response_server)))
                }
        }
}
