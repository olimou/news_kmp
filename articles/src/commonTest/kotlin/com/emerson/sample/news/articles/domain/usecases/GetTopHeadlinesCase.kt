package com.emerson.sample.news.articles.domain.usecases

import com.emerson.sample.news.articles.Fixture.articleModelList
import com.emerson.sample.news.articles.domain.repositories.IArticleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class GetTopHeadlinesCaseTest {
    private val repository: IArticleRepository = mockk()
    lateinit var useCase: GetTopHeadlinesCase

    @BeforeTest
    fun setup() {
        useCase = GetTopHeadlinesCase(repository)
    }

    @AfterTest
    fun tearDown() {
        confirmVerified(
            repository,
        )
    }

    @Test
    fun `should call repository to get top headlines`() = runBlocking {
        coEvery { repository.getTopHeadlines() } returns articleModelList

        val result = useCase()

        assertEquals(articleModelList, result)

        coVerify { repository.getTopHeadlines() }
    }

    @Test
    fun `should return empty list when repository returns empty`() = runBlocking {
        coEvery { repository.getTopHeadlines() } returns emptyList()

        val result = useCase()

        assertEquals(emptyList(), result)

        coVerify { repository.getTopHeadlines() }
    }

    @Test
    fun `should throws exception when repository throws exception`() {
        runBlocking {
            coEvery { repository.getTopHeadlines() } throws Exception()

            // check if the exception is thrown
            runCatching {
                useCase()
            }.onFailure {
                assert(it is Exception)
            }.onSuccess {
                assert(false)
            }

            coVerify { repository.getTopHeadlines() }
        }
    }
}