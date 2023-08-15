package com.monzo.androidtest.articles.domain

import java.util.Date

data class Article(
        val thumbnail: String,
        val published: Date,
        val title: String,
        val url: String
)
