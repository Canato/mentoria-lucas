package com.monzo.androidtest

import android.app.Application
import android.content.Context
import com.monzo.androidtest.articles.ArticlesModule
import com.monzo.androidtest.individual.IndividualModule

class HeadlinesApp : Application() {
    private val articlesModule = ArticlesModule()
    private val individualModule = IndividualModule()

    companion object {
        fun from(applicationContext: Context): ArticlesModule {
            return (applicationContext as HeadlinesApp).articlesModule
        }
        fun from2(applicationContext: Context): IndividualModule{
            return (applicationContext as HeadlinesApp).individualModule
        }
    }
}
