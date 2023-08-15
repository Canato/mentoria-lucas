package com.monzo.androidtest.articles.data

import com.monzo.androidtest.api.model.ApiArticleListResponse
import com.monzo.androidtest.articles.domain.Article

class ArticleMapper {

    fun map(apiArticleListResponse: ApiArticleListResponse): List<Article> =
        apiArticleListResponse.response.results.mapNotNull { article ->
            val thumbnail: String? = article.fields?.thumbnail
            val headline: String = article.fields?.headline ?: article.webTitle
            if (thumbnail != null) Article(
                thumbnail = thumbnail,
                published = article.webPublicationDate,
                title = headline,
                url = article.apiUrl,
            )
            else null
        }
}
