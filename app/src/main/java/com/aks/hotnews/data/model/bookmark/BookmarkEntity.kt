package com.aks.hotnews.data.model.bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: Int,
    val author: String?,
    val authors: String,
    val category: String?,
    val image: String?,
    val language: String?,
    val publish_date: String?,
    val sentiment: Double?,
    val source_country: String?,
    val summary: String?,
    val text: String?,
    val title: String?,
    val url: String?,
    val video: String?
)