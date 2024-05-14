package com.example.newsapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.components.NewsVerticalCard
import com.example.newsapp.viewModel.AppViewModelProvider
import com.example.newsapp.viewModel.DetailViewModel
import com.example.newsapp.viewModel.FavoriteViewModel

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val articles by viewModel.favoriteArticles.collectAsState(initial = emptyList())

    Box(
      modifier = Modifier
          .fillMaxSize()
          .padding(all = 16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Favorite News",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(articles) {article ->
                NewsVerticalCard(article = article)
            }
        }
    }
}