package com.aks.hotnews.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.aks.hotnews.data.model.bookmark.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}