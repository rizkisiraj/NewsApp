package com.example.newsapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.example.newsapp.utils.Formatter
import com.example.newsapp.viewModel.AppViewModelProvider
import com.example.newsapp.viewModel.DetailViewModel
import com.example.newsapp.viewModel.NewsViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    newsUUID: String
) {
    val detailViewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val coroutineScope = rememberCoroutineScope()
    val article: MutableState<Article?> = remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        var dumpArticle = newsViewModel.headlineArticles.value.find { it.id.toString() == newsUUID }
        if(dumpArticle == null) {
            dumpArticle = newsViewModel.recommendedArticles.value.find { it.id.toString() == newsUUID }
        }
        article.value = dumpArticle
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 16.dp)
    ) {
        Column {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                          IconButton(onClick = {
                              coroutineScope.launch {
                                  detailViewModel.saveItem(article.value!!)
                              }
                          }) {
                              Icon(painter = painterResource(R.drawable.baseline_star_outline_24), contentDescription = null)
                          }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors()
            )
            Text(
                article.value?.title ?: "",
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = article.value?.urlToImage ?: "",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.failed_placeholder),
                contentDescription = "Placeholder Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp)

            )

            Spacer(modifier = Modifier
                .height(8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = article.value?.author ?: "",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    text = Formatter.formatDate(dateString = article.value?.publishedAt ?: ""),
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.value?.description ?: "",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}