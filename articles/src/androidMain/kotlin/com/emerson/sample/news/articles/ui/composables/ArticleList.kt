package com.emerson.sample.news.articles.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emerson.sample.news.articles.domain.models.ArticleModel
import com.emerson.sample.news.articles.ui.ArticleResult
import com.emerson.sample.news.articles.viewModels.ArticleListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleList(
    viewModel: ArticleListViewModel = koinViewModel(),
) {
    MaterialTheme {
        val articleState = viewModel.articleState.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                when (val result = articleState.value) {
                    is ArticleResult.Loading -> LoadingView()
                    is ArticleResult.Success -> ArticleList(result.items)
                    is ArticleResult.Error -> ErrorView(
                        message = result.message,
                        retry = { viewModel.loadData() }
                    )

                    else -> {}
                }
            }
        }
    }
}


@Composable
fun ArticleList(article: List<ArticleModel>) {
    LazyColumn {
        items(article) { item ->
            ArticleItem(article = item)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    ArticleList()
}