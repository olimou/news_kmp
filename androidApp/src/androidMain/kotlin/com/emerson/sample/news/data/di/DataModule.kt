package com.emerson.sample.news.data.di

import com.emerson.sample.news.BuildConfig
import com.emerson.sample.news.articles.data.mappers.MapArticlePayload
import com.emerson.sample.news.articles.data.mappers.MapSourcePayload
import com.emerson.sample.news.articles.data.repositories.ArticleRepository
import com.emerson.sample.news.articles.data.source.cloud.ArticleSourceCloud
import com.emerson.sample.news.articles.domain.repositories.IArticleRepository
import com.emerson.sample.news.core.network.HttpClientProvider
import org.koin.dsl.module

object DataModule {
    val module = module {
        single { HttpClientProvider() }
        single {
            ArticleSourceCloud(
                apiKey = BuildConfig.NEWS_API_KEY,
                clientProvider = get(),
            )
        }
        single { MapSourcePayload() }
        single {
            MapArticlePayload(
                mapSourcePayload = get(),
            )
        }
        single<IArticleRepository> {
            ArticleRepository(
                articleSourceCloud = get(),
                mapArticlePayload = get(),
            )
        }
    }
}