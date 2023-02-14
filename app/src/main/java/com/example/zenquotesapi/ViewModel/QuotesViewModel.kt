package com.example.zenquotesapi.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zenquotesapi.Extra.Events
import com.example.zenquotesapi.Models.Quotes
import com.example.zenquotesapi.Repository.QuoteRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuotesViewModel: ViewModel(){

    val quoteRepository: QuoteRepository = QuoteRepository()
    val data = MutableLiveData<Events<Quotes>>()

    fun get(){
        viewModelScope.launch {
            quoteRepository.getQuotesFromServer().catch {
                Log.e("VM", "get: ${it.localizedMessage}")
                data.postValue(Events.Error(msg = it.localizedMessage))
            }.collect{
                data.postValue(Events.Success(it))
            }
        }
    }
}