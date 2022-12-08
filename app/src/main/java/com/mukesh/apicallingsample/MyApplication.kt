package com.mukesh.apicallingsample

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.mukesh.apicallingsample.data.api.APIService
import com.mukesh.apicallingsample.data.api.RetrofitHelper

class MyApplication : Application(), LifecycleObserver {
    companion object {
        var mInstance: MyApplication? = null
    }

    var apiService: APIService? = null
    var activity: Activity? = null

    @Synchronized
    fun getInstance(): MyApplication? {
        return MyApplication.mInstance
    }


    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        apiService = RetrofitHelper.getInstance(applicationContext)
        mInstance = this
    }

}