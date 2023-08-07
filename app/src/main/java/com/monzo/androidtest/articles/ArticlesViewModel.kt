package com.monzo.androidtest.articles

import com.monzo.androidtest.articles.model.Article
import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel(
    private val repository: ArticlesRepository
) : BaseViewModel<ArticlesState>(ArticlesState()) {
    private fun changeArticlesTitle (articles: List<Article>, step: Int = 0) : List<Article> {
        val newArticle = articles.mapIndexed { index, article ->
            if (index % step == 0) {
                article.copy(title = "LUCAS")
            } else article
        }
        return newArticle
    }
    init {
        disposables += repository.latestFintechArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles ->
                val newArticles = changeArticlesTitle(articles, step = 2)
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
                val newArticles = changeArticlesTitle(articles, step = 5)
                state.value?.copy(refreshing = false, articles = newArticles)?.let { newState ->
                    setState(newState)
                }
            }
    }
}


data class ArticlesState(
    val refreshing: Boolean = true,
    val articles: List<Article> = emptyList()
)
