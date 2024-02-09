package com.emerson.sample.news.articles.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emerson.sample.news.articles.domain.models.ArticleModel
import com.emerson.sample.news.articles.domain.repositories.IArticleRepository
import com.emerson.sample.news.articles.ui.ArticleResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val articleRepository: IArticleRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _articleState: MutableStateFlow<ArticleResult> = MutableStateFlow(
        ArticleResult.Initial
    )
    val articleState: StateFlow<ArticleResult>
        get() = _articleState

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(dispatcher) {
            setLoading()
            println("ArticleListViewModel - loadData - articleRepository.getArticles()")
            try {
                val articles = articleRepository.getArticles()
                setSuccess(articles)
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    private fun setError(e: Exception) {
        _articleState.value = ArticleResult.Error(e.message ?: "An unexpected error occurred")
    }

    private fun setLoading() {
        _articleState.value = ArticleResult.Loading
    }

    private fun setSuccess(items: List<ArticleModel>) {
        _articleState.value = ArticleResult.Success(items)
    }
}