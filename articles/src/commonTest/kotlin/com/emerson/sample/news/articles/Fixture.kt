package com.emerson.sample.news.articles

import com.emerson.sample.news.articles.data.payloads.ArticlesPayload
import com.emerson.sample.news.articles.domain.models.ArticleModel
import com.emerson.sample.news.articles.domain.models.ArticleModel.Source
import kotlinx.datetime.Clock

object Fixture {
    private const val title = "title"
    private const val description = "description"
    private const val content = "content"
    private const val url = "url"
    private const val urlToImage = "urlToImage"
    private val publishedAt = Clock.System.now().toString()
    private const val publishedAtFormatted = "now"
    private const val author = "author"
    private const val sourceId = "sourceId"
    private const val sourceName = "sourceName"

    val sourceModel = Source(
        id = sourceId,
        name = sourceName
    )
    val articleModel = ArticleModel(
        title = title,
        description = description,
        content = content,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        publishedAtFormatted = publishedAtFormatted,
        author = author,
        source = sourceModel
    )

    val sourcePayload = ArticlesPayload.Article.Source(
        id = sourceId,
        name = sourceName
    )

    val articlePayload = ArticlesPayload.Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = sourcePayload,
        title = title,
        url = url,
        urlToImage = urlToImage
    )

    val articlesPayload = ArticlesPayload(
        articles = listOf(articlePayload)
    )

    val emptyArticlePayload = ArticlesPayload(
        articles = emptyList()
    )
}