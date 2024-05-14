package com.example.newsapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.model.BottomNavObject
import com.example.newsapp.screen.DetailScreen
import com.example.newsapp.screen.FavoriteScreen
import com.example.newsapp.screen.HomeScreen
import com.example.newsapp.viewModel.NewsViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {

    val newsViewModel: NewsViewModel = NewsViewModel()

    NavHost(navController = navController, startDestination = BottomNavObject.Home.route) {
        composable(route = BottomNavObject.Home.route) {
            HomeScreen(navController = navController, viewModel = newsViewModel)
        }

        composable(route = BottomNavObject.Favorite.route) {
            FavoriteScreen()
        }

        composable(
            route = "detail/{newsUUID}",
            arguments = listOf(navArgument("newsUUID") { type = NavType.StringType })
        ) {navBackStackEntry ->
            val newsUUID = navBackStackEntry.arguments?.getString("newsUUID")
            newsUUID?.let {
                DetailScreen(newsViewModel = newsViewModel, newsUUID = newsUUID, navController = navController)
            }
        }
    }
}