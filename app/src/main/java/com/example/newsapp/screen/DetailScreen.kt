package com.example.newsapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@Preview
@Composable
fun Coba() {
    DetailScreen()
}

@Composable
fun DetailScreen() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 16.dp)
    ) {
        Column {
            Text(
                "Ancelotti will leave Real Madrid at the end of the season",
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "Placeholder Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "July 14, 2024",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ronaldo is widely regarded as the greatest football player of all time. His unmatched skills on the field have earned him numerous accolades and records. With his speed, agility, and goal-scoring ability, Ronaldo has dazzled fans around the world. His dedication and hard work have propelled him to the pinnacle of success in the sport. Ronaldo's impact on the game will be remembered for generations to come.\n" +
                        "\n" +
                        "Ronaldo's influence extends beyond the football field. His philanthropic efforts and commitment to social causes have made him a role model for many. Through his charitable work and advocacy, Ronaldo has touched the lives of numerous individuals in need. His efforts to give back to the community have solidified his legacy as not only a great player but also a compassionate human being.",
                textAlign = TextAlign.Justify
            )
        }
    }
}