package com.emerson.sample.news.articles.data.mappers

import com.emerson.sample.news.articles.data.payloads.ArticlesPayload
import com.emerson.sample.news.articles.domain.models.ArticleModel

class MapSourcePayload {
    fun map(sourcePayload: ArticlesPayload.Article.Source): ArticleModel.Source {
        return ArticleModel.Source(
            id = sourcePayload.id,
            name = sourcePayload.name
        )
    }
}