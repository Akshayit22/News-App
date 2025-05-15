package com.aks.hotnews.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aks.hotnews.data.model.bookmark.BookmarkEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: BookmarkEntity)

    @Delete
    suspend fun delete(article: BookmarkEntity)

    @Query("SELECT * FROM bookmarks")
    suspend fun getAll(): List<BookmarkEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    suspend fun isBookmarked(id: Int): Boolean
}