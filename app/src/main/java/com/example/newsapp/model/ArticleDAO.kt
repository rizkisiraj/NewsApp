package com.example.newsapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Article)

    @Update
    suspend fun update(item: Article)

    @Delete
    suspend fun delete(item: Article)

    @Query("SELECT * from articles WHERE id = :id")
    fun getItem(id: UUID): Flow<Article>

    @Query("SELECT * from articles ORDER BY id ASC")
    fun getAllItems(): Flow<List<Article>>

    @Query("SELECT * from articles WHERE title = :title")
    fun getItemByTitle(title: String): Flow<Article?>
}