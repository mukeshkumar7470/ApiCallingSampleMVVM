package com.mukesh.apicallingsample.data.repository

import android.content.Context
import com.mukesh.apicallingsample.MyApplication

class AppRepository(
    applicationContext: Context
) {
    var apiService = (applicationContext as MyApplication).apiService

    suspend fun doLogin(username: String?, password: String?) =
        apiService?.loginApi(username, password)

}