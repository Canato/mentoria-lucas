package com.monzo.androidtest.articles.presentation


data class ArticlesState(
    val refreshing: Boolean = true,
    val articles: List<ArticleItem> = emptyList()
)

data class ArticleItem(
    val thumbnail: String,
    val published: String,
    val title: String,
    val url: String
)