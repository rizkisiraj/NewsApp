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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.components.NewsHorizontalCard
import com.example.newsapp.components.NewsVerticalCard

@Preview
@Composable
fun HomeScreen() {
    val rowScrollState = rememberScrollState()
    val parentScrollState = rememberScrollState()

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
           Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
            ) {
                NewsHorizontalCard()
                NewsHorizontalCard()
                NewsHorizontalCard()
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