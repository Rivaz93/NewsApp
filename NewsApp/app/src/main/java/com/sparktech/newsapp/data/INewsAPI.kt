package com.sparktech.newsapp.data

import com.sparktech.newsapp.pojo.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface INewsAPI {

    @GET("v1/articles")
    fun getNewsList(@Query("source") source : String,
                    @Query("sortBy") sortBy : String,
                    @Query("apiKey") apiKey : String) : Observable<Response>


}