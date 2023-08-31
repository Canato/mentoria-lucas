package com.monzo.androidtest.individual.data


import com.monzo.androidtest.api.model.ApiArticle
import com.monzo.androidtest.api.model.ApiArticleResponse
import com.monzo.androidtest.individual.domain.ArticleContent

class IndividualMapper {
    fun map(apiArticleResponse: ApiArticleResponse): ArticleContent {
        val article = apiArticleResponse.response.content
        val thumbnail: String = article.fields?.thumbnail!!
        val headline: String = article.fields.headline ?: article.webTitle
        val body: String = article.fields.body!!
        return ArticleContent(thumbnail, headline, body)
    }
}