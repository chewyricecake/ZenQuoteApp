package com.example.zenquotesapi.Services

import com.example.zenquotesapi.Models.Quotes
import retrofit2.http.GET

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuotes(): Quotes

}