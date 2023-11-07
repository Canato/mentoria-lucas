package com.monzo.androidtest.articles.presentation

import com.monzo.androidtest.articles.domain.Article
import org.junit.Test
import org.junit.Assert.assertEquals
import java.text.SimpleDateFormat
import java.util.Date

class ArticleSectionMapperTest {
    @Test
    fun testMapToSection() {
        val articles = listOf(
            Article("Article 1", Date(),
                title = "quidam",
                url = "https://search.yahoo.com/search?p=mollis", ),
            Article("Article 2", Date()),

        )

        val result = ArticleSectionMapper.mapToSection(articles)


        assertEquals(3, result.size)
    }

}