package com.example.newsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.remote.data.News

interface IRepository {

    fun getLatestNews(): LiveData<List<News>>

    fun updateLatestNews()

    val isProcessing: MutableLiveData<Boolean>
}