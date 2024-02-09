package com.emerson.sample.news.articles.domain.models

data class ArticleModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val publishedAtFormatted: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) {
    data class Source(
        val id: String? = null,
        val name: String? = null
    )
}