package com.example.newsapp.model.repository

import com.example.newsapp.model.local.AppDatabase
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.remote.data.OldNews
import com.example.newsapp.utils.formatDate

class LocalRepository(private val appDatabase: AppDatabase) {

    fun getLatestNews() = appDatabase.getNewsDao().getNews()

    fun saveNews(news: List<News>) = appDatabase.getNewsDao().saveNews(news)

    fun saveOldNews(news: List<OldNews>) = appDatabase.getNewsDao().saveOldNews(news)

    fun getNews(
        start_date: String,
        end_date: String,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ) = appDatabase.getNewsDao().getOldNews(
        start_date.formatDate(),
        end_date.formatDate(),
        category,
        country,
        language
    )
}