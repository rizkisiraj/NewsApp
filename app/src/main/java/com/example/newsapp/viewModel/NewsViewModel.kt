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

class NewsViewModel: ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>>
        get() = _articles

    var headlinesState = mutableStateOf<LoadingState>(LoadingState.LOADING)
    fun getHeadlines() {
        headlinesState.value = LoadingState.LOADING
        viewModelScope.launch {
            try {
                val response = ApiService.newsApi.getTopHeadlines()
                _articles.value = response.articles
                headlinesState.value = LoadingState.SUCCESS
            } catch (e: HttpException) {
                headlinesState.value = LoadingState.FAILED
                Log.e("Error",e.message())
            }
        }
    }
}