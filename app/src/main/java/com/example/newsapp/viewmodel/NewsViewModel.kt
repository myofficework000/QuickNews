package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.repository.IRepository

class NewsViewModel(application: Application, private val repository: IRepository) :
    AndroidViewModel(application) {

    val latestNews: LiveData<List<News>> = repository.getLatestNews()
    val isProcessing: LiveData<Boolean> = repository.isProcessing

    fun refreshNews() {
        repository.updateLatestNews()
    }
}