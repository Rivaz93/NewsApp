package com.sparktech.newsapp.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPI {

    fun getAPI() : INewsAPI {
        return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://newsapi.org")
                .build()
                .create(INewsAPI::class.java)

    }
}