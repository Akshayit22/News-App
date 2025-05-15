package com.aks.hotnews.data.db.repository

import com.aks.hotnews.data.db.BookmarkDao
import com.aks.hotnews.data.model.bookmark.toBookmarkEntity
import com.aks.hotnews.data.model.bookmark.toNews
import com.aks.hotnews.data.model.news.News

class BookmarkRepository(private val dao: BookmarkDao) {

    suspend fun add(news: News) = dao.insert(news.toBookmarkEntity())

    suspend fun remove(news: News) = dao.delete(news.toBookmarkEntity())

    suspend fun getAll(): List<News> = dao.getAll().map { it.toNews() }

    suspend fun isBookmarked(id: Int): Boolean = dao.isBookmarked(id)
}