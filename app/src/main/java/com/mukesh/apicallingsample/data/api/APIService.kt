package com.mukesh.apicallingsample.data.api

import com.mukesh.apicallingsample.presentation.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface APIService {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginApi(
        @Field("email") username: String?,
        @Field("password") password: String?
    ): Response<LoginResponse>

}