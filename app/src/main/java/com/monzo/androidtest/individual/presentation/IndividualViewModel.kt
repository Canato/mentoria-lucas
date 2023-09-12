package com.monzo.androidtest.individual.presentation

import android.widget.Toast
import com.monzo.androidtest.common.BaseViewModel
import com.monzo.androidtest.individual.data.IndvArticlesRepository
import com.monzo.androidtest.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class IndividualViewModel(
    private val repository: IndvArticlesRepository,
    private val url: String
) : BaseViewModel<IndividualState>(IndividualState(null)) {


    init {
        disposables += repository.getArticle(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ article ->
                val newArticle = IndividualArticle(
                    thumbnail = article.thumbnail,
                    headline = article.headline,
                    text = article.body
                )
                state.value?.copy(article = newArticle)?.let { newState ->
                    setState(newState)
                }

            }, Timber::e)

    }
}