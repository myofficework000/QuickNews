package com.example.newsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.remote.data.News

interface IRepository {

    fun getLatestNews(): LiveData<List<News>>

    fun updateLatestNews()

    fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String? = null,
        country: String? = null,
        language: String? = null
    )

    val isProcessing: MutableLiveData<Boolean>
    val searchedNews: MutableLiveData<List<News>>
}