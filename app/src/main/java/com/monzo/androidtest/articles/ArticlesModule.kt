package com.monzo.androidtest.articles

import android.content.Context
import com.monzo.androidtest.api.GuardianService
import com.monzo.androidtest.articles.data.ArticlesRepository
import com.monzo.androidtest.articles.presentation.ArticlesViewModel
import com.monzo.androidtest.articles.data.ArticleMapper
import com.monzo.androidtest.common.injection.NetworkModule
import com.monzo.androidtest.articles.presentation.ArticleSectionMapper
import com.monzo.androidtest.common.DateProviderImp

class ArticlesModule {
    fun inject(context: Context): ArticlesViewModel {
        val networkModule = NetworkModule(context = context)
        val guardianService = networkModule.provideRetrofit().create(GuardianService::class.java)
        return ArticlesViewModel(
            repository = ArticlesRepository(
                guardianService = guardianService,
                articleMapper = ArticleMapper()
            ),
            mapper = ArticleSectionMapper(dateProvider = DateProviderImp())
        )
    }
}