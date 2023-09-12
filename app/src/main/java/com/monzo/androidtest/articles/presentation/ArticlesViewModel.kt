package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.data.ArticlesRepository
import com.monzo.androidtest.articles.domain.Article
import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat

class ArticlesViewModel(
    private val repository: ArticlesRepository
) : BaseViewModel<ArticlesState>(ArticlesState()) {
    private fun changeArticlesTitle(articles: List<ArticleItem>, step: Int = 0): List<ArticleItem> {
        val newArticle = articles.mapIndexed { index, article ->
            if (index % step == 0) {
                article.copy(title = "LUCAS")
            } else article
        }
        return newArticle
    }


    init {
        disposables += repository.latestFintechArticles().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles ->
                val articlesItemList = articles.map { article ->
                    ArticleItem(
                        thumbnail = article.thumbnail,
                        published = SimpleDateFormat("dd/MM/yyyy").run { format(article.published) },
                        title = article.title,
                        url = article.url
                    )
                }
                val newArticles = changeArticlesTitle(articlesItemList, step = 2)
                state.value?.copy(refreshing = false, articles = newArticles)?.let { newState ->
                    setState(newState)
                }
            }

    }

    fun onRefresh() {
        state.value?.copy(refreshing = true)?.let { newState -> setState(newState) }
        disposables += repository.latestFintechArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles ->
                val articlesItemList = articles.map { article ->
                    ArticleItem(
                        thumbnail = article.thumbnail,
                        published = SimpleDateFormat("dd/MM/yyyy").run { format(article.published) },
                        title = article.title,
                        url = article.url
                    )
                }
                val newArticles = changeArticlesTitle(articlesItemList, step = 5)
                state.value?.copy(refreshing = false, articles = newArticles)?.let { newState ->
                    setState(newState)
                }
            }
    }
}



