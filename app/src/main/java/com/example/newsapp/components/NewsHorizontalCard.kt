package com.example.newsapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@Preview
@Composable
fun NewsHorizontalCard() {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(190.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Placeholder Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Black.copy(alpha = 0.4f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
            ) {
                Text(
                    "Trending",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Text(
                "Regulation has been imponded to be the maximum",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}