package com.example.newsapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.model.local.AppDatabase
import com.example.newsapp.model.remote.ApiService
import com.example.newsapp.model.remote.RetrofitBuilder
import com.example.newsapp.model.repository.LocalRepository
import com.example.newsapp.model.repository.RemoteRepository
import com.example.newsapp.model.repository.Repository
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.createFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setUpObserver()

        binding.btnSearch.setOnClickListener { _ ->
            binding.edtSearch.text.toString().takeIf { x -> x.isNotBlank() }?.let {
                viewModel.searchNews(
                    it,
                    System.currentTimeMillis() - 14 * 86400000, // This is 2 weeks
                    System.currentTimeMillis()
                )
            }
        }
    }

    private fun setUpObserver() {
        viewModel.isProcessing.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE


            }
        }

        viewModel.searchedNews.observe(this) {
            binding.rvNews.adapter = NewsRvAdapter(it)
        }

        viewModel.latestNews.observe(this) {
            binding.rvNews.layoutManager = LinearLayoutManager(this)
            binding.rvNews.adapter = NewsRvAdapter(it)
        }
    }

    private fun initViewModel() {
        val remoteRepository =
            RemoteRepository(RetrofitBuilder.getRetrofit().create(ApiService::class.java))
        val localRepository = LocalRepository(AppDatabase.getInstance(this.applicationContext))
        val repository = Repository(localRepository, remoteRepository)
        val factory = NewsViewModel(application, repository).createFactory()
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}