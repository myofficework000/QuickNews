package com.example.newsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.newsapp.R
import com.squareup.picasso.Picasso

class CommonBindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("remoteSourceImage")
        fun loadImageFromServer(imageView: ImageView, url: String?) = with(url) {
            if (!isNullOrEmpty() && contains("https://")) {
                Picasso
                    .get()
                    .load(this)
                    .error(R.drawable.baseline_error_24)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        }
    }
}