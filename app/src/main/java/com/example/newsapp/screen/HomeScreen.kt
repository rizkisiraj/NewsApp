package com.example.newsapp.screen

import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.components.NewsHorizontalCard
import com.example.newsapp.components.NewsVerticalCard
import com.example.newsapp.components.shimmerEffect
import com.example.newsapp.model.LoadingState
import com.example.newsapp.viewModel.NewsViewModel

@Preview
@Composable
fun HomeScreen() {
    val rowScrollState = rememberScrollState()
    val parentScrollState = rememberScrollState()
    val viewModel = NewsViewModel()
    val items = viewModel.articles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHeadlines()
    }

    Box(
      modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()
          .padding(all = 16.dp)
    ){
       Column(
            modifier = Modifier
                .verticalScroll(parentScrollState)
       ) {
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
               if(viewModel.headlinesState.value == LoadingState.LOADING) {
                   items(2) {it ->
                       Box(modifier = Modifier.width(280.dp).height(190.dp).clip(RoundedCornerShape(10.dp)).shimmerEffect())
                   }
               } else {
                   items(items.value) { article ->
                       NewsHorizontalCard(article = article)
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
           Column(
               verticalArrangement = Arrangement.spacedBy(16.dp)
           ) {
               NewsVerticalCard()
               NewsVerticalCard()
               NewsVerticalCard()
               NewsVerticalCard()
               NewsVerticalCard()
               NewsVerticalCard()
               NewsVerticalCard()
           }
        }
    }
}