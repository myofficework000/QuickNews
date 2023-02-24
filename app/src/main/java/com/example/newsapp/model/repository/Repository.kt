package com.example.newsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.remote.data.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : IRepository {

    override val isProcessing = MutableLiveData<Boolean>()

    override fun getLatestNews(): LiveData<List<News>> {
        updateLatestNews()
        return localRepository.getLatestNews()
    }

    override fun updateLatestNews() {
        val call: Call<NewsResponse> = remoteRepository.loadLatestNews()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                isProcessing.postValue(false)
                if (!response.isSuccessful) {
                    return
                }

                val newsResponse: NewsResponse = response.body() ?: return

                if (newsResponse.status == "ok") {
                    localRepository.saveNews(newsResponse.news)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                isProcessing.postValue(false)
                t.printStackTrace()
            }
        })
    }
}