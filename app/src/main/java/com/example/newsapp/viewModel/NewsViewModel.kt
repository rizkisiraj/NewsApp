package com.example.newsapp.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.model.LoadingState
import com.example.newsapp.utils.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class NewsViewModel: ViewModel() {
    private val _headlineArticles = MutableStateFlow<List<Article>>(emptyList())
    val headlineArticles: StateFlow<List<Article>>
        get() = _headlineArticles

    private val _recommendedArticles = MutableStateFlow<List<Article>>(emptyList())
    val recommendedArticles: StateFlow<List<Article>>
        get() = _recommendedArticles

    var headlinesState = mutableStateOf<LoadingState>(LoadingState.LOADING)
    var newsState = mutableStateOf<LoadingState>(LoadingState.LOADING)
    fun getHeadlines() {
        viewModelScope.launch {
            try {
                val response = ApiService.newsApi.getTopHeadlines()
                _headlineArticles.value = response.articles
                headlinesState.value = LoadingState.SUCCESS
            } catch (e: HttpException) {
                headlinesState.value = LoadingState.FAILED
                Log.e("Error",e.message())
            }
        }
    }

    fun getNews() {
        newsState.value = LoadingState.LOADING
        viewModelScope.launch {
            try {
                val response = ApiService.newsApi.getNews()
                _recommendedArticles.value = response.articles
                newsState.value = LoadingState.SUCCESS
            } catch (e: HttpException) {
                newsState.value = LoadingState.FAILED
                Log.e("Error",e.message())
            }
        }
    }
}