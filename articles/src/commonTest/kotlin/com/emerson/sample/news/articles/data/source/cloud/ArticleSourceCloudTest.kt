package com.emerson.sample.news.articles.data.source.cloud

import com.emerson.sample.news.articles.Fixture.articlesPayload
import com.emerson.sample.news.core.network.HttpClientProvider
import com.google.gson.Gson
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleSourceCloudTest {
    private lateinit var articleSourceCloud: ArticleSourceCloud

    @Test
    fun `test getArticles`() {
        runBlocking {
            val response = Gson().toJson(articlesPayload)

            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(response),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            val httpClient = HttpClientProvider(mockEngine)

            articleSourceCloud = ArticleSourceCloud(
                clientProvider = httpClient
            )

            val payload = articleSourceCloud.getTopHeadlines()
            assertEquals(articlesPayload, payload)
        }
    }

    @Test
    fun `test getArticles with error`() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(""),
                    status = HttpStatusCode.InternalServerError,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            val httpClient = HttpClientProvider(mockEngine)

            articleSourceCloud = ArticleSourceCloud(
                clientProvider = httpClient
            )

            runCatching {
                articleSourceCloud.getTopHeadlines()
            }.onFailure {
                assertEquals("Error fetching articles", it.message)
            }
        }
    }
}