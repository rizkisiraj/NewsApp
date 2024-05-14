package com.example.newsapp.utils

import com.example.newsapp.model.Article
import com.example.newsapp.model.ArticleDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Article::class.java, ArticleDeserializer())
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val newsApi: NewsAPI = retrofit.create(NewsAPI::class.java)
}