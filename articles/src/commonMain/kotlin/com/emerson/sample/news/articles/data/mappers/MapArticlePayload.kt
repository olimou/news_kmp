package com.emerson.sample.news.articles.data.mappers

import com.emerson.sample.news.articles.data.payloads.ArticlesPayload
import com.emerson.sample.news.articles.domain.models.ArticleModel
import com.emerson.sample.news.core.ext.datetime.timesAgo
import com.emerson.sample.news.core.ext.datetime.toDateTime

class MapArticlePayload(
    private val mapSourcePayload: MapSourcePayload,
) {
    fun map(articlesPayload: ArticlesPayload.Article): ArticleModel {
        return ArticleModel(
            author = articlesPayload.author,
            content = articlesPayload.content,
            description = articlesPayload.description,
            publishedAt = articlesPayload.publishedAt,
            publishedAtFormatted = publishedAtFormatted(articlesPayload),
            source = articlesPayload.source?.let { mapSourcePayload.map(it) },
            title = articlesPayload.title,
            url = articlesPayload.url,
            urlToImage = articlesPayload.urlToImage
        )
    }

    private fun publishedAtFormatted(articlesPayload: ArticlesPayload.Article) =
        articlesPayload.publishedAt.toDateTime().timesAgo()
}

