package com.example.newsapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): Long =
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").parse(this)?.time ?: 0L

@SuppressLint("SimpleDateFormat")
fun Long.toNewsDate(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(this)
