package com.example.newsapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.remote.data.News

class NewsRvAdapter(private val newsList: List<News>) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: NewsItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.news_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setData(newsList[position])
    }

    override fun getItemCount() = newsList.size
}