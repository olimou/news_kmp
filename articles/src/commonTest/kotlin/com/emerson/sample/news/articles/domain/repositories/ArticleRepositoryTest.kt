package com.emerson.sample.news.articles.domain.repositories

import com.emerson.sample.news.articles.Fixture.articleModel
import com.emerson.sample.news.articles.Fixture.articlePayload
import com.emerson.sample.news.articles.Fixture.articlesPayload
import com.emerson.sample.news.articles.data.mappers.MapArticlePayload
import com.emerson.sample.news.articles.data.repositories.ArticleRepository
import com.emerson.sample.news.articles.data.source.cloud.ArticleSourceCloud
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleRepositoryTest {
    private var articleSourceCloud: ArticleSourceCloud = mockk()
    private var mapArticlePayload: MapArticlePayload = mockk()

    private lateinit var articleRepository: ArticleRepository

    @BeforeTest
    fun setup() {
        articleRepository = ArticleRepository(
            articleSourceCloud = articleSourceCloud,
            mapArticlePayload = mapArticlePayload
        )
    }

    @AfterTest
    fun tearDown() {
        confirmVerified(
            articleSourceCloud,
            mapArticlePayload,
        )
    }

    @Test
    fun `test getArticles`() {
        coEvery { articleSourceCloud.getTopHeadlines() } returns articlesPayload
        coEvery { mapArticlePayload.map(any()) } returns articleModel

        runTest {
            val articles = articleRepository.getTopHeadlines()
            assertEquals(listOf(articleModel), articles)
        }

        coVerify(exactly = 1) {
            articleSourceCloud.getTopHeadlines()
        }

        verify(exactly = 1) {
            mapArticlePayload.map(articlePayload)
        }
    }

    @Test
    fun `test getArticles with error`() {
        coEvery { articleSourceCloud.getTopHeadlines() } throws Exception("Error fetching articles")

        runTest {
            runCatching {
                articleRepository.getTopHeadlines()
            }.exceptionOrNull()
                .also { assertEquals("Error fetching articles", it?.message) }

            coVerify(exactly = 1) {
                articleSourceCloud.getTopHeadlines()
            }
        }
    }
}