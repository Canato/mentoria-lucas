package com.monzo.androidtest.individual.data

import com.monzo.androidtest.api.GuardianService
import com.monzo.androidtest.api.model.ApiArticle
import com.monzo.androidtest.articles.data.ArticleMapper
import com.monzo.androidtest.individual.domain.ArticleContent
import io.reactivex.Single


class IndvArticlesRepository(
    private val guardianService: GuardianService,
    private val individualMapper: IndividualMapper
) {

    fun getArticle(articleUrl: String): Single<ArticleContent> {
        return guardianService.getArticle(articleUrl, "main,body,headline,thumbnail")
            .map { response ->
                individualMapper.map(response)
            }
    }
}