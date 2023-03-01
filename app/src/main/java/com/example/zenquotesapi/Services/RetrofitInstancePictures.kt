package com.example.zenquotesapi.Services

import com.example.zenquotesapi.Extra.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancePictures {
    val retrofit by lazy{
        Retrofit.Builder().baseUrl(Utils.UNSPLASH_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}