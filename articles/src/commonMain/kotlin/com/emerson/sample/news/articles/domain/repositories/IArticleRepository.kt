package com.emerson.sample.news.articles.domain.repositories

import com.emerson.sample.news.articles.domain.models.ArticleModel

interface IArticleRepository {
    suspend fun getTopHeadlines(): List<ArticleModel>
}