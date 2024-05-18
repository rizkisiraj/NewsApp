package com.example.newsapp

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.model.Article
import com.example.newsapp.model.BottomNavObject
import com.example.newsapp.screen.DetailScreen
import com.example.newsapp.screen.FavoriteScreen
import com.example.newsapp.screen.HomeScreen
import com.example.newsapp.viewModel.AppViewModelProvider
import com.example.newsapp.viewModel.DetailViewModel
import com.example.newsapp.viewModel.NewsViewModel
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun BottomNavGraph(navController: NavHostController) {

    val newsViewModel: NewsViewModel = NewsViewModel()

    NavHost(navController = navController, startDestination = BottomNavObject.Home.route) {
        composable(route = BottomNavObject.Home.route) {
            HomeScreen(navController = navController, viewModel = newsViewModel)
        }

        composable(route = BottomNavObject.Favorite.route) {
            FavoriteScreen(navController)
        }

        composable(
            route = "detail/{path}/{newsUUID}",
            arguments = listOf(navArgument("path") { type = NavType.StringType },navArgument("newsUUID") { type = NavType.StringType })
        ) {navBackStackEntry ->
            val coroutineScope = rememberCoroutineScope()
            val path = navBackStackEntry.arguments?.getString("path")
            val newsUUID = navBackStackEntry.arguments?.getString("newsUUID")
            val detailViewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)

            path?.let {
                if(path == "news") {
                    var dumpArticle = newsViewModel.headlineArticles.value.find { it.id.toString() == newsUUID }
                    if(dumpArticle == null) {
                        dumpArticle = newsViewModel.recommendedArticles.value.find { it.id.toString() == newsUUID }
                    }
                    detailViewModel.updateArticle(dumpArticle)
                    DetailScreen(navController = navController, detailViewModel = detailViewModel)
                } else if(path == "favorite") {
                    val uuidString = UUID.fromString(newsUUID)
                    detailViewModel.getArticleFromLocal(uuidString)
                    DetailScreen(navController = navController, detailViewModel = detailViewModel)
                }
            }
        }
    }
}