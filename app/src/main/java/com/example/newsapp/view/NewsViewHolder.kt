package com.example.newsapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.remote.data.News

class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(news: News) {
        binding.news = news
    }
}