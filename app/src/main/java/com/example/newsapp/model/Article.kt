package com.example.newsapp.model

import java.util.UUID

data class Article(
    val id: UUID = UUID.randomUUID(),
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null
)


