package com.emerson.sample.news.presentation.di

import com.emerson.sample.news.articles.viewModels.ArticleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UiModule {
    val module = module {
        viewModel { ArticleListViewModel(get()) }
    }
}