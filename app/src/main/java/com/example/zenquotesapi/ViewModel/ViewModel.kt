package com.example.zenquotesapi.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zenquotesapi.Extra.Events
import com.example.zenquotesapi.Models.Quotes.Quotes
import com.example.zenquotesapi.Models.Quotes.RandomQuote
import com.example.zenquotesapi.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val Repository: Repository): ViewModel(){

    val quotes = MutableLiveData<Events<Quotes>>()
    val randomQuote = MutableLiveData<RandomQuote>()
    val randomPicture = MutableLiveData<String>()

    fun getQuotes(){
        viewModelScope.launch {
            Repository.getQuotesFromServer().catch {
                Log.e("VM", "getQuotes: ${it.localizedMessage}")
                quotes.postValue(Events.Error(msg = it.localizedMessage))
            }.collect{
                quotes.postValue(Events.Success(it))
            }
        }
    }

    fun getRandomQuote(){
        viewModelScope.launch {
            Repository.getRandomQuoteFromServer().catch {
                Log.e("VM", "getRandomQuote: ${it.localizedMessage}")
            }.collect{
                randomQuote.value = it[0]
            }
        }
    }

    fun getRandomPicture(){
        viewModelScope.launch {
            Repository.getRandomPictureFromServer().catch {
                Log.e("VM", "getRandomQuote: ${it.localizedMessage}")
            }.collect{
                randomPicture.value = it.urls.regular
            }
        }
    }
}
