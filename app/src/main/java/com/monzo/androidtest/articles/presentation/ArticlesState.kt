package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.domain.Article


data class ArticlesState(
    val refreshing: Boolean = true,
    val articleSection: List<ArticleSection> = emptyList()
)

data class ArticleItem(
    val thumbnail: String,
    val published: String,
    val title: String,
    val url: String
)

data class ArticleSection(
    val title: String,
    val articles: List<ArticleItem>
)