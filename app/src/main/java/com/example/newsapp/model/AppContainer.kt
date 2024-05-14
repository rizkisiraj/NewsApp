package com.example.newsapp.model

import android.content.Context

interface AppContainer {
    val activitiesRepository: ActivitiesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineActivitiesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ActivitiesRepository]
     */
    override val activitiesRepository: ActivitiesRepository by lazy {
        OfflineActivitiesRepository(AppDatabase.getDatabase(context).activityDao())
    }
}