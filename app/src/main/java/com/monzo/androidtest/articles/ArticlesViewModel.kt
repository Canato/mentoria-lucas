package com.monzo.androidtest.articles

import com.monzo.androidtest.articles.model.Article
import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel(
    private val repository: ArticlesRepository
) : BaseViewModel<ArticlesState>(ArticlesState()) {
    private fun changeArticlesTitle (articles: List<Article>, step: Int = 0, refresh: Boolean) {
        val newArticle = articles.mapIndexed { index, article ->
            if (index % step == 0) {
                article.copy(title = "LUCAS")
            } else article

        }
        state.value?.copy(refreshing = refresh, articles = newArticle)?.let { newState ->
            setState(newState)
        }
    }
    init {
        disposables += repository.latestFintechArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles -> changeArticlesTitle(articles, step = 2, refresh = false) }

    }
    fun onRefresh() {
        disposables += repository.latestFintechArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles -> changeArticlesTitle(articles, step = 5, refresh = true)
            }
        state.value?.copy(refreshing = false)?.let { newState -> setState(newState) }
    }
}


data class ArticlesState(
    val refreshing: Boolean = true,
    val articles: List<Article> = emptyList()
)
