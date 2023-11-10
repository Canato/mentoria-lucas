package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.data.ArticlesRepository
import com.monzo.androidtest.articles.domain.Article
import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat

class ArticlesViewModel(
    private val repository: ArticlesRepository,
    private val mapper: ArticleSectionMapper
) : BaseViewModel<ArticlesState>(ArticlesState()) {
    init {
        disposables += repository.latestFintechArticles().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles ->

                val sections = mapper.mapToSection(articles)

                state.value?.copy(refreshing = false, articleSection = sections)?.let { newState ->
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

                val sections = mapper.mapToSection(articles)

                state.value?.copy(refreshing = false, articleSection = sections)?.let { newState ->
                    setState(newState)
                }
            }
    }

}