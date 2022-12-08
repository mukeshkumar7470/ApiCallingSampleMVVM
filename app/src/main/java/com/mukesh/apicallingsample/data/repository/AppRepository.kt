package com.mukesh.apicallingsample.data.repository

import android.content.Context
import android.util.Log
import com.mukesh.apicallingsample.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Field
import retrofit2.http.POST

class AppRepository(applicationContext: Context) {
    var apiService = (applicationContext as MyApplication).apiService

     suspend fun login(username: String, password: String) =
        apiService?.doLogin(username, password)

}