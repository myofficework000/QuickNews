package com.example.newsapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.remote.data.News
import com.squareup.picasso.Picasso

class NewsRvAdapter(private val context: Context, private val newsList: List<News>) :
    Adapter<NewsRvAdapter.NewsViewHolder>() {
    private lateinit var binding: NewsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = NewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.apply {
            val item = newsList[position]
            item.apply {
                newsTitle.text = title
                //newsCategory.text = category?.get(0) ?: ""
                newsAuthor.text = author
                val url = newsList[position].image
                Picasso.get().load(url).into(newsImg)
            }

            itemView.setOnClickListener {
                /*  val intent = Intent(context, NewsDetailsActivity::class.java)
                  intent.putExtra("news",item)
                  context.startActivity(intent)*/
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(view: View) : ViewHolder(view) {
        val newsTitle = binding.txtTitle
        val newsCategory = binding.txtCategory
        val newsImg = binding.imgNews
        val newsAuthor = binding.txtAuthor
    }
}