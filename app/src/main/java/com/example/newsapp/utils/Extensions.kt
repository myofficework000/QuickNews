package com.example.newsapp.utils

import java.text.SimpleDateFormat
import java.util.Date


// Example: 2019-08-04 14:22:08 +0000
fun String.formatDate(): Long =
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").parse(this)?.time?:0L
fun Long.toNewsDate(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(this)
