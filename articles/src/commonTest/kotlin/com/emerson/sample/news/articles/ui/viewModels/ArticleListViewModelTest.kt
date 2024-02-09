package com.emerson.sample.news.articles.ui.viewModels

import com.emerson.sample.news.articles.Fixture.articleModel
import com.emerson.sample.news.articles.domain.usecases.GetTopHeadlinesCase
import com.emerson.sample.news.articles.ui.ArticleResult
import com.emerson.sample.news.articles.viewModels.ArticleListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleListViewModelTest {
    private val topHeadlinesCase: GetTopHeadlinesCase = mockk()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var articleListViewModel: ArticleListViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadData success - should return loading and success`() {
        runTest {
            val items = listOf(articleModel)
            val results = mutableListOf<ArticleResult>()

            coEvery { topHeadlinesCase() } coAnswers {
                delay(1000)
                items
            }

            articleListViewModel = ArticleListViewModel(
                topHeadlinesCase = topHeadlinesCase,
                dispatcher = testDispatcher,
            )

            articleListViewModel.articleState
                .take(2)
                .collect {
                    results.add(it)
                }

            assertEquals(2, results.size)

            assertTrue(results[0] is ArticleResult.Loading)
            assertTrue(results[1] is ArticleResult.Success)

            coVerify { topHeadlinesCase() }
        }
    }

    @Test
    fun `loadData error - should return loading and error`() {
        runTest {
            val results = mutableListOf<ArticleResult>()

            coEvery { topHeadlinesCase() } coAnswers {
                delay(1000)
                throw Exception("An unexpected error occurred")
            }

            articleListViewModel = ArticleListViewModel(
                topHeadlinesCase = topHeadlinesCase,
                dispatcher = testDispatcher,
            )

            articleListViewModel.articleState
                .take(2)
                .collect {
                    results.add(it)
                }

            assertEquals(2, results.size)

            assertTrue(results[0] is ArticleResult.Loading)
            assertTrue(results[1] is ArticleResult.Error)

            coVerify { topHeadlinesCase() }

        }
    }
}