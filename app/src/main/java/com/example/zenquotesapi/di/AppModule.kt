package com.example.zenquotesapi.di

import com.example.zenquotesapi.Extra.Utils
import com.example.zenquotesapi.Repository.Repository
import com.example.zenquotesapi.Services.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(apiInterface: ApiInterface): Repository = Repository(apiInterface)

    @Singleton
    @Provides
    fun provideQuoteRetrofitInstance() : ApiInterface{
        return Retrofit.Builder()
            .baseUrl(Utils.QUOTE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

/*    @Singleton
    @Provides
    fun providePictureRetrofitInstance() : ApiInterface{
        return Retrofit.Builder()
            .baseUrl(Utils.UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }*/


}