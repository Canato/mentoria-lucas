package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.domain.Article
import com.monzo.androidtest.common.DateProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.assertEquals
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ArticleSectionMapperTest {

    private val dateProvider: DateProvider = mockk()
    private val mapper: ArticleSectionMapper = ArticleSectionMapper(dateProvider)
    @Test
    fun `GIVEN articles with different weeks WHEN mapping THEN show different sections`() {
        // GIVEN
        val testCalendar = Calendar.getInstance()
        testCalendar.set(2023,10, 3,20,0,0)
        every { dateProvider.getCurrentDate() } returns testCalendar

        val articles = listOf(
            Article(
                thumbnail = "Article 1",
                published = Calendar.getInstance().run{
                    set(2023,10, 1, 20,0,0)
                    time
                },
                title = "quidam",
                url = "https://search.yahoo.com/search?p=mollis"
            ),
            Article("Article 2",
                published =Calendar.getInstance().run{
                    set(2023,9, 24, 18,0,0)
                    time
                },
                title = "unum",
                url = "https://www.google.com/#q=omittantur",
            ),
            Article(
                thumbnail = "Article 3",
                published =Calendar.getInstance().run{
                    set(2023,9, 19, 5,0,0)
                    time
                },
                title = "inimicus",
                url = "http://www.bing.com/search?q=interesset"
            )
        )

        // WHEN
        val result = mapper.mapToSection(articles)

        // THEN
        val expected = listOf(
            ArticleSection(
                title = "This Week",
                articles = listOf(ArticleItem(
                    thumbnail = "Article 1",
                    published = "01/11/2023",
                    title = "quidam",
                    url = "https://search.yahoo.com/search?p=mollis"))
            ),
            ArticleSection(
                title = "Last Week",
                articles = listOf(ArticleItem(
                    thumbnail = "Article 2",
                    published = "24/10/2023",
                    title = "unum",
                    url = "https://www.google.com/#q=omittantur"))
            ),
            ArticleSection(
                title = "Older Articles",
                articles = listOf(ArticleItem(
                    thumbnail = "Article 3",
                    published = "19/10/2023",
                    title = "inimicus",
                    url = "http://www.bing.com/search?q=interesset"))
            )
        )

        assertEquals(expected, result)
    }

}