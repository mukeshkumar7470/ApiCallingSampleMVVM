package com.mukesh.apicallingsample.presentation.login.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mukesh.apicallingsample.data.repository.AppRepository
import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        repository.login("eve.holt@reqres.in", "cityslicka").collect {
            if (it != null) {
                it.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.d("success", "res :${response.toString()}")
                        val dataTOken = response.body()
                        Log.d("success", "$dataTOken")
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.d("Failure", "error")
                    }
                })
            }
        }
    }


}