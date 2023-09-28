package com.monzo.androidtest.individual.presentation

import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.individual.data.IndvArticlesRepository
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class IndividualViewModel(
    repository: IndvArticlesRepository,
    url: String
) : BaseViewModel<IndividualState>(IndividualState(null)) {


    init {
        disposables += repository.getArticle(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { article ->
                val newArticle = IndividualArticle(
                    thumbnail = article.thumbnail,
                    headline = article.headline,
                    body = article.body
                )
                state.value?.copy(article = newArticle)?.let { newState ->
                    setState(newState)
                }

            }

    }
}