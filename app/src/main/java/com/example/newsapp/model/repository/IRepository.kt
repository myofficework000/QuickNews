package com.example.newsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.remote.data.News
import io.reactivex.rxjava3.disposables.Disposable

interface IRepository {

    fun getLatestNews(): LiveData<List<News>>

    fun updateLatestNews(): Disposable
    fun getNewsByRegion(region: String) : LiveData<List<News>>

    fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ): Disposable

    val isProcessing: MutableLiveData<Boolean>
    val searchedNews: MutableLiveData<List<News>>
    val regionNews: MutableLiveData<List<News>>
}