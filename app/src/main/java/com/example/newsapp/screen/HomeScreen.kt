package com.example.newsapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.components.NewsHorizontalCard
import com.example.newsapp.components.NewsVerticalCard
import com.example.newsapp.components.shimmerEffect
import com.example.newsapp.model.LoadingState
import com.example.newsapp.viewModel.NewsViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: NewsViewModel) {
    val headlines = viewModel.headlineArticles.collectAsState()
    val news = viewModel.recommendedArticles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeadlines()
    }

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    Box(
      modifier = Modifier
          .fillMaxSize()
          .padding(all = 16.dp)
    ){
       LazyColumn(
           modifier = Modifier
               .fillMaxSize()
       ) {
           item{
               Text(
                   text = "Populer",
                   fontSize = 24.sp,
                   fontWeight = FontWeight.Bold
               )
               Spacer(modifier = Modifier.height(8.dp))
               LazyRow(
                   horizontalArrangement = Arrangement.spacedBy(16.dp),
                   userScrollEnabled = true
               ) {
                   if (viewModel.headlinesState.value == LoadingState.LOADING) {
                       items(2) { _ ->
                           Box(
                               modifier = Modifier
                                   .width(280.dp)
                                   .height(190.dp)
                                   .clip(RoundedCornerShape(10.dp))
                                   .shimmerEffect()
                           )
                       }
                   } else {
                       items(headlines.value) { article ->
                           NewsHorizontalCard(article = article, modifier = Modifier.clickable {
                               val articleString: String = article.id.toString()
                               navController.navigate("detail/news/${articleString}")
                           })
                       }
                   }

               }

               Spacer(modifier = Modifier.height(24.dp))

               Text(
                   text = "Rekomendasi",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold
               )
               Spacer(modifier = Modifier.height(8.dp))
           }
           
               items(news.value) {article ->
                   NewsVerticalCard(article = article, modifier = Modifier.clickable {
                       val articleString: String = article.id.toString()
                       navController.navigate("detail/news/${articleString}")
                   })
                   Spacer(modifier = Modifier.height(16.dp))
               }
           
           item {
               Spacer(modifier = Modifier.height(70.dp))
           }
        }
    }
}