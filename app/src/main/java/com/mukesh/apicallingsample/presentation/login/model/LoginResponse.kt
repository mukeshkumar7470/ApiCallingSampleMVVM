package com.mukesh.apicallingsample.presentation.login.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class LoginResponse(
    @SerializedName("token")
    val token: String,
): Serializable