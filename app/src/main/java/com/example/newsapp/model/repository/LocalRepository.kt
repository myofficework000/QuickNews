package com.example.newsapp.model.repository

import com.example.newsapp.model.local.AppDatabase
import com.example.newsapp.model.remote.data.News

class LocalRepository(private val appDatabase: AppDatabase) {

    fun getLatestNews() = appDatabase.getNewsDao().getNews()

    fun saveNews(news: List<News>) = appDatabase.getNewsDao().saveNews(news)
}