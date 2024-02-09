package com.emerson.sample.news.articles.domain.usecases

import com.emerson.sample.news.articles.domain.repositories.IArticleRepository

class GetTopHeadlinesCase(
    private val repository: IArticleRepository,
) {
    suspend operator fun invoke() = repository.getTopHeadlines()
}