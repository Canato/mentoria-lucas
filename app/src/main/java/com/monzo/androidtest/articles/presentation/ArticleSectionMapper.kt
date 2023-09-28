package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.domain.Article
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object ArticleSectionMapper {
    fun mapToSection(articles: List<Article>): List<ArticleSection> {

        val currentDate = Date()
        val calendar = Calendar.getInstance()

        calendar.time = currentDate

        val currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)

        val articlesThisWeek = mutableListOf<Article>()
        val articlesLastWeek = mutableListOf<Article>()
        val articlesOlder = mutableListOf<Article>()

        for (article in articles) {
            calendar.time = article.published
            val articleWeek = calendar.get(Calendar.WEEK_OF_YEAR)
            val weekDiff = currentWeek - articleWeek

            if (weekDiff == 0) {
                articlesThisWeek.add(article)
            } else if (weekDiff == 1) {
                articlesLastWeek.add(article)
            } else {
                articlesOlder.add(article)
            }
        }
        val articleItemThisWeek = mapToArticleItem(articlesThisWeek)
        val articleItemLastWeek = mapToArticleItem(articlesLastWeek)
        val articleItemOlder = mapToArticleItem(articlesOlder)



        return listOf(
            ArticleSection("This Week", articleItemThisWeek),
            ArticleSection("Last Week", articleItemLastWeek),
            ArticleSection("Older Articles", articleItemOlder)
        )


    }

    private fun mapToArticleItem(mutableList: MutableList<Article>): List<ArticleItem> =
        mutableList.map { article ->
            ArticleItem(
                thumbnail = article.thumbnail,
                published = SimpleDateFormat("dd/MM/yyyy").run { format(article.published) },
                title = article.title,
                url = article.url
            )
        }

}