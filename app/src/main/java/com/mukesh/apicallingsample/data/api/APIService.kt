package com.mukesh.apicallingsample.data.api

import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface APIService {

    @FormUrlEncoded
    @POST("login")
    fun doLogin(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<LoginResponse>
}