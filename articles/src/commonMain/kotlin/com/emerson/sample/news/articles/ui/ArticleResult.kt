package com.emerson.sample.news.articles.ui

import com.emerson.sample.news.articles.domain.models.ArticleModel

sealed class ArticleResult {
    data object Initial : ArticleResult()
    data object Loading : ArticleResult()
    class Success(val items: List<ArticleModel>) : ArticleResult()
    class Error(val message: String) : ArticleResult()
}