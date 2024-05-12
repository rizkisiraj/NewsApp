package com.example.newsapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Article
import com.example.newsapp.utils.ApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel: ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    fun getHeadlines() {
        viewModelScope.launch {
            try {
                val response = ApiService.newsApi.getTopHeadlines()
                _articles.value = response.articles
            } catch (e: HttpException) {
                Log.e("GPTview","a")
            }
        }
    }
}