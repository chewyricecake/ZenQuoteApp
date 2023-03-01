package com.example.zenquotesapi.Services

import android.graphics.Picture
import com.example.zenquotesapi.Models.*
import com.example.zenquotesapi.Models.Pictures.Pictures
import com.example.zenquotesapi.Models.Quotes.Quotes
import com.example.zenquotesapi.Models.Quotes.RandomQuotes
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuotes(): Quotes

    @GET("random")
    suspend fun getRandomQuote(): RandomQuotes

    @GET("photos/random")
    suspend fun getRandomPicture(@Query("client_id") accessKey: String): Pictures
}