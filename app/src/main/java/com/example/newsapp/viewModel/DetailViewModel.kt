package com.example.newsapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsapp.model.ActivitiesRepository
import com.example.newsapp.model.Article

class DetailViewModel(private val activitiesRepository: ActivitiesRepository): ViewModel() {


    suspend fun saveItem(activity: Article) {
        activitiesRepository.insertActivity(activity)
    }

}