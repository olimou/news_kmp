package com.emerson.sample.news.articles.data.repositories

import com.emerson.sample.news.articles.data.mappers.MapArticlePayload
import com.emerson.sample.news.articles.data.source.cloud.ArticleSourceCloud
import com.emerson.sample.news.articles.domain.models.ArticleModel
import com.emerson.sample.news.articles.domain.repositories.IArticleRepository

class ArticleRepository(
    private val articleSourceCloud: ArticleSourceCloud,
    private val mapArticlePayload: MapArticlePayload,
) : IArticleRepository {
    override suspend fun getTopHeadlines(): List<ArticleModel> {
        return articleSourceCloud.getTopHeadlines()
            .articles
            .orEmpty()
            .mapNotNull { payload ->
                payload?.let { model -> mapArticlePayload.map(model) }
            }
    }
}