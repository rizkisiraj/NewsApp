package com.example.newsapp.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.LearningApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            DetailViewModel(learningApplication().container.activitiesRepository)
        }

        initializer {
            FavoriteViewModel(learningApplication().container.activitiesRepository)
        }
        // Initializer for ItemEntryViewModel
    }
}
fun CreationExtras.learningApplication(): LearningApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LearningApplication)