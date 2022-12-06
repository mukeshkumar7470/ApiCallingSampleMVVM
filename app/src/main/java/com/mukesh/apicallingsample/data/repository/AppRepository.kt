package com.mukesh.apicallingsample.data.repository

import android.content.Context
import android.util.Log
import com.mukesh.apicallingsample.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository(
    applicationContext: Context
) {
    var apiService = (applicationContext as MyApplication).apiService

    fun login(username: String, password: String) = flow {
        val response = apiService?.doLogin(username,password)
        emit(response)
    }.catch {
        Log.e("TAG", "Login failed. ErrorHint: ${it.message}", )
    }.flowOn(Dispatchers.IO)
}