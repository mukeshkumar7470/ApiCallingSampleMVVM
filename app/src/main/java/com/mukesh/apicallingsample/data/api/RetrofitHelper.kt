package com.mukesh.apicallingsample.data.api

import android.content.Context
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getInstance(context: Context): APIService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(Interceptor addInterceptor@{ chain: Interceptor.Chain ->
                var response: Response? = null
                try {
                    val request = chain.request()
                    response = chain.proceed(request)
                    if (response.code == 200) {

                    }
                    if (response.code == 401) {

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return@addInterceptor response ?: Response.Builder()
                    .code(200).request(chain.request())
                    .protocol(Protocol.HTTP_1_0).message("asd")
                    .body("asd".toResponseBody()).build()
            })
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(APIService::class.java)
    }
}