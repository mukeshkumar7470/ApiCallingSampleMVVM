package com.mukesh.apicallingsample.presentation.login.model

import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("token")
    var token: String? = null
}