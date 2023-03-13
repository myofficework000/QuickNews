package com.example.newsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.remote.data.OldNews
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class Repository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : IRepository {

    override val isProcessing = MutableLiveData<Boolean>()
    override val searchedNews = MutableLiveData<List<News>>()
    override val regionNews = MutableLiveData<List<News>>()

    override fun getLatestNews(): LiveData<List<News>> {
        updateLatestNews()
        return localRepository.getLatestNews()
    }

    override fun updateLatestNews() = remoteRepository.loadLatestNews()
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                isProcessing.postValue(false)
                if (it.status == "ok") localRepository.saveNews(it.news)
            },
            {
                isProcessing.postValue(false)
                it.printStackTrace()
            }
        )

    override fun getNewsByRegion(region: String): LiveData<List<News>> {
        val call: Call<NewsResponse> = remoteRepository.getNewsbyRegion(region)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse: NewsResponse = response.body() ?: return
                    if(newsResponse.status == "ok")
                    regionNews.postValue(newsResponse.news)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                isProcessing.postValue(false)
                t.printStackTrace()
            }

        })
        return  localRepository.getNewsByRegion()
    }

    override fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String?,
        country: String?,
        language: String?
    ) = remoteRepository.searchNews(
        keywords,
        start_date,
        end_date,
        category,
        country,
        language
    )
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe({
            isProcessing.postValue(false)
            if (it.status == "ok")
                localRepository.saveOldNews(it.news.map { x->OldNews.fromNews(x) })
            searchedNews.postValue(it.news)
        },{
            isProcessing.postValue(false)
            it.printStackTrace()
        }).also { isProcessing.postValue(true) }
}