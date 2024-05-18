package com.example.newsapp.model

import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Repository that provides insert, update, delete, and retrieve of [Activity] from a given data source.
 */
interface ActivitiesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllActivitiesStream(): Flow<List<Article>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getActivityStream(id: UUID): Flow<Article?>

    fun getActivityStream(title: String): Flow<Article?>

    /**
     * Insert item in the data source
     */
    suspend fun insertActivity(item: Article)

    /**
     * Delete item from the data source
     */
    suspend fun deleteActivity(item: Article)

    /**
     * Update item in the data source
     */
    suspend fun updateActivity(item: Article)
}