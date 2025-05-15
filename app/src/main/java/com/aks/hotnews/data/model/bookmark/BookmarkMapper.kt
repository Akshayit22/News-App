package com.aks.hotnews.data.model.bookmark

import com.aks.hotnews.data.model.news.News

fun News.toBookmarkEntity(): BookmarkEntity = BookmarkEntity(
    id = id,
    author = author ?: "Unknown",
    authors = authors?.joinToString(",") ?: "",
    category = category ?: "General",
    image = image ?: "",
    language = language ?: "en",
    publish_date = publish_date ?: "",
    sentiment = sentiment,
    source_country = source_country ?: "",
    summary = summary ?: "",
    text = text ?: "",
    title = title ?: "",
    url = url ?: "",
    video = video?.toString() ?: ""
)

fun BookmarkEntity.toNews(): News = News(
    id = id,
    author = author ?: "Unknown",
    authors = if (authors.isNotBlank()) authors.split(",") else emptyList(),
    category = category ?: "General",
    image = image ?: "",
    language = language ?: "en",
    publish_date = publish_date ?: "",
    sentiment = sentiment ?: 0.0,
    source_country = source_country ?: "",
    summary = summary ?: "",
    text = text ?: "",
    title = title ?: "",
    url = url ?: "",
    video = video
)