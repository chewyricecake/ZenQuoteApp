package com.example.zenquotesapi.Repository

import com.example.zenquotesapi.Extra.Utils
import com.example.zenquotesapi.Models.Pictures.Pictures
import com.example.zenquotesapi.Models.Quotes.Quotes
import com.example.zenquotesapi.Models.Quotes.RandomQuotes
import com.example.zenquotesapi.Services.ApiInterface
import com.example.zenquotesapi.Services.RetrofitInstancePictures
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(val apiInterface: ApiInterface){

    fun getQuotesFromServer() : Flow<Quotes> = flow{
        emit(apiInterface.getQuotes())
    }.flowOn(Dispatchers.Main)

    fun getRandomQuoteFromServer() : Flow<RandomQuotes> = flow{
        emit(apiInterface.getRandomQuote())
    }.flowOn(Dispatchers.Main)

    fun getRandomPictureFromServer() : Flow<Pictures> = flow{
        emit(RetrofitInstancePictures.apiInterface.getRandomPicture(Utils.UNSPLASH_ACCESS_KEY))
    }.flowOn(Dispatchers.Main)

}