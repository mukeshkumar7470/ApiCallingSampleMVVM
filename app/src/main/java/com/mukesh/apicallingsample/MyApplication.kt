package com.mukesh.apicallingsample

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.mukesh.apicallingsample.data.api.APIService
import com.mukesh.apicallingsample.data.api.RetrofitHelper.getInstance

class MyApplication : Application(), LifecycleObserver {
    var mInstance: MyApplication? = null
    var apiService: APIService? = null

    @Synchronized
    fun getInstance(): MyApplication? {
        return mInstance
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        apiService = getInstance(applicationContext)
        mInstance = this
    }

}