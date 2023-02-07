package com.example.zenquotesapi.Services

import com.example.zenquotesapi.Extra.Utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val ApiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}