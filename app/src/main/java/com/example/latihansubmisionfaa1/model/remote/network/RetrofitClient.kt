package com.example.latihansubmisionfaa1.model.remote.network

import com.example.latihansubmisionfaa1.BuildConfig
import com.example.latihansubmisionfaa1.model.remote.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }
}