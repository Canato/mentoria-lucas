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

        val thisWeek = mutableListOf<Article>()
        val lastWeek = mutableListOf<Article>()
        val older = mutableListOf<Article>()

        for (article in articles) {
            calendar.time = article.published
            val articleWeek = calendar.get(Calendar.WEEK_OF_YEAR)
            val weekDiff = currentWeek - articleWeek

            if (weekDiff == 0) {
                thisWeek.add(article)
            } else if (weekDiff == 1) {
                lastWeek.add(article)
            } else {
                older.add(article)
            }
        }
        thisWeek.sortByDescending { it.published }
        lastWeek.sortByDescending { it.published }
        older.sortByDescending { it.published }
        val articleThisWeek = mapToArticleItem(thisWeek)
        val articleLastWeek = mapToArticleItem(lastWeek)
        val articleOlder = mapToArticleItem(older)



        return listOf(
            ArticleSection("This Week", articleThisWeek),
            ArticleSection("Last Week", articleLastWeek),
            ArticleSection("Older Articles", articleOlder)
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