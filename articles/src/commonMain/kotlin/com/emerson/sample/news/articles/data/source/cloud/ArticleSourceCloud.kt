package com.emerson.sample.news.articles.data.source.cloud

import com.emerson.sample.news.articles.data.payloads.ArticlesPayload
import com.emerson.sample.news.articles.data.source.cloud.Articles.Companion.URL
import com.emerson.sample.news.core.network.HttpClientProvider
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.resources.Resource

@Resource("/")
class Articles {
    companion object {
        const val URL = "newsapi.org/v2"
    }

    @Resource("top-headlines")
    class TopHeadlines(
        val country: String,
        val category: String,
    )
}

class ArticleSourceCloud(
    private val apiKey: String,
    private val clientProvider: HttpClientProvider,
) {
    private val builder: HttpRequestBuilder.() -> Unit = {
        url {
            host = URL
            protocol = URLProtocol.HTTPS
            parameters.append("apiKey", apiKey)
        }
    }

    suspend fun getTopHeadlines(
        country: String = "us",
        category: String = "business",
    ): ArticlesPayload {
        try {
            val httpResponse = clientProvider.httpClient().get(
                resource = Articles.TopHeadlines(
                    country = country,
                    category = category,
                ),
                builder = builder,
            )

            if (httpResponse.status != HttpStatusCode.OK) {
                throw Exception("Error fetching articles")
            }

            return httpResponse.body<ArticlesPayload>()
        } catch (e: Exception) {
            throw Exception("Error fetching articles")
        }
    }
}