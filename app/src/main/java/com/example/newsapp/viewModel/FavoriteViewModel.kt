package com.example.newsapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsapp.model.ActivitiesRepository

class FavoriteViewModel(private val activitiesRepository: ActivitiesRepository): ViewModel() {
    val favoriteArticles = activitiesRepository.getAllActivitiesStream()
}