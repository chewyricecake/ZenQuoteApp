package com.example.zenquotesapi.Repository

import com.example.zenquotesapi.Models.Quotes
import com.example.zenquotesapi.Services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuoteRepository {

    fun getQuotesFromServer() : Flow<Quotes> = flow{
        emit(RetrofitInstance.ApiInterface.getQuotes())
    }.flowOn(Dispatchers.Main)

}