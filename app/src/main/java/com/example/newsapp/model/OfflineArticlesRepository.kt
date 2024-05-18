package com.example.newsapp.model

import kotlinx.coroutines.flow.Flow
import java.util.UUID

class OfflineActivitiesRepository(private val ActivityDao: ActivityDao) : ActivitiesRepository {
    override fun getAllActivitiesStream(): Flow<List<Article>> = ActivityDao.getAllItems()

    override fun getActivityStream(id: UUID): Flow<Article?> = ActivityDao.getItem(id)

    override fun getActivityStream(title: String): Flow<Article?> = ActivityDao.getItemByTitle(title)

    override suspend fun insertActivity(item: Article) = ActivityDao.insert(item)

    override suspend fun deleteActivity(item: Article) = ActivityDao.delete(item)

    override suspend fun updateActivity(item: Article) = ActivityDao.update(item)
}