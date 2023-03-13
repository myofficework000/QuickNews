package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.repository.IRepository
import com.example.newsapp.utils.toNewsDate
import io.reactivex.rxjava3.disposables.CompositeDisposable

class NewsViewModel(application: Application, private val repository: IRepository) :
    AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    val newsByRegion: LiveData<List<News>> = repository.regionNews
    val latestNews: LiveData<List<News>> = repository.getLatestNews()
    val searchedNews: LiveData<List<News>> = repository.searchedNews
    val isProcessing: LiveData<Boolean> = repository.isProcessing

    fun refreshNews() {
        repository.updateLatestNews()
            .also { compositeDisposable.add(it) }
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
        ).also { compositeDisposable.add(it) }
    }

    fun getNewsByRegion(region: String) {
        repository.getNewsByRegion(region)
            .also { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}