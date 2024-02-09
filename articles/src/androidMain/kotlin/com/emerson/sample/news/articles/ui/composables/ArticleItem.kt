package com.emerson.sample.news.articles.ui.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emerson.sample.news.articles.domain.models.ArticleModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleItem(article: ArticleModel) {
    val context = LocalContext.current
    
    Card(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            article.url?.let { url ->
                openUrl(
                    context = context,
                    url = url,
                )
            }
        }
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                model = article.urlToImage.orEmpty(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
            ) {
                Text(
                    text = article.title.orEmpty(),
                    style = MaterialTheme.typography.h6,
                    maxLines = 3,
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            bottom = 16.dp,
                            end = 16.dp,
                        )
                        .weight(1f),
                    text = article.source?.name.orEmpty(),
                    style = MaterialTheme.typography.caption,
                )
                Text(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            bottom = 16.dp,
                            end = 16.dp,
                        ),
                    text = article.publishedAtFormatted.orEmpty(),
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

private fun openUrl(context: Context, url: String) {
    context.startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                url
            )
        )
    )
}