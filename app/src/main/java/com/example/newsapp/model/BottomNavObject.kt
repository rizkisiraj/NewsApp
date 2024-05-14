package com.example.newsapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavObject(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomNavObject(
        route = "news",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Favorite: BottomNavObject(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Filled.Favorite
    )
}