package com.example.newsapp.utils

import com.example.newsapp.model.Article
import retrofit2.http.GET

interface NewsAPI {
    @GET("top-headlines?country=us&apiKey=6d7bd721446a4c0abf575b03a97d501f&pageSize=5")
    suspend fun getTopHeadlines(): NewsApiResponse
}

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    var articles: List<Article>
)