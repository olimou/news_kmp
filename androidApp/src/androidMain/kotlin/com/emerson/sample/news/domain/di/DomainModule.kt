package com.emerson.sample.news.domain.di

import com.emerson.sample.news.articles.domain.usecases.GetTopHeadlinesCase
import org.koin.dsl.module

object DomainModule {
    val module = module {
        single { GetTopHeadlinesCase(get()) }
    }
}