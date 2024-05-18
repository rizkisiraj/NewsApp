package com.example.newsapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.ActivitiesRepository
import com.example.newsapp.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class DetailViewModel(private val activitiesRepository: ActivitiesRepository): ViewModel() {

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean>
        get() = _isLiked

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?>
        get() = _article

    suspend fun saveItem(activity: Article) {
        activitiesRepository.insertActivity(activity)
    }

    suspend fun checkIfIsLiked(title: String) {
        val activity = activitiesRepository.getActivityStream(title)
        viewModelScope.launch {
            activity.collect { article ->
                if(article != null ){
                    _isLiked.value = true
                }
            }
        }
    }

    fun updateArticle(article: Article?) {
        _article.value = article
    }

    fun getArticleFromLocal(uuid: UUID) {
        val activity = activitiesRepository.getActivityStream(uuid)
        viewModelScope.launch {
            activity.collect { article ->
                updateArticle(article)
            }
        }
    }
}