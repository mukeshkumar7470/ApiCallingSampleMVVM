package com.mukesh.apicallingsample.presentation.login.model

import com.google.gson.annotations.SerializedName

class UserData {
    @SerializedName("token")
    lateinit var token: String
}