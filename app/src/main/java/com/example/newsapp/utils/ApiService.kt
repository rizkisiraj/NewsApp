package com.example.newsapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsApi: NewsAPI = retrofit.create(NewsAPI::class.java)
}