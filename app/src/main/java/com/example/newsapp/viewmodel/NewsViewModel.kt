package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.repository.IRepository
import com.example.newsapp.utils.toNewsDate

class NewsViewModel(application: Application, private val repository: IRepository) :
    AndroidViewModel(application) {

    val latestNews: LiveData<List<News>> = repository.getLatestNews()
    val searchedNews: LiveData<List<News>> = repository.searchedNews
    val isProcessing: LiveData<Boolean> = repository.isProcessing

    fun refreshNews() {
        repository.updateLatestNews()
    }

    fun searchNews(
        keywords: String,
        start_date: Long,
        end_date: Long,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ) {
        repository.searchNews(
            keywords,
            start_date.toNewsDate(),
            end_date.toNewsDate(),
            category,
            country,
            language
        )
    }
}